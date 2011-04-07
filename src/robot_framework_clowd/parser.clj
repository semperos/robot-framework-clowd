(ns robot-framework-clowd.parser
  (:require [clojure.string :as str]
            [clj-webdriver.core :as wd] :reload))

(defn parse-regex
  "Given a value, detect whether or not it should be a regex and convert appropriately"
  [s]
  (if (re-find #"^/.*?(?<!\\)/$" s)
    (re-pattern (subs s 1 (dec (count s))))
    s))

(defn handle-nums
  "Cast things which should be numbers to numbers (everything from RF comes in as a string)"
  [m]
  (if (contains? m :index)
    (assoc m :index (dec (Integer. (:index m)))) ; human-friendly coming in
    m))

(defn clean-file-path
  "Convert file separators"
  [s]
  (str/replace s "\\" "/"))

(defn parse-descriptor
  "Given the arguments to a keyword from RobotFramework, parse the arguments string and return a data structure needed by clj-webdriver"
  [^String descriptor]
  (if (or (nil? descriptor) (empty? descriptor) (= "nil" (.toLowerCase descriptor)))
    nil
    (let [descriptions (remove empty? (str/split descriptor #"(?<!\\),"))]
      (-> (zipmap
           (for [desc descriptions]
             (-> (re-find #"^([^=]+)=" desc)
                 second
                 str/trim
                 .toLowerCase
                 (str/replace "_" "-")
                 keyword))
           (for [desc descriptions]
             (-> (re-find #"^[^=]+=(.*?)$" desc)
                 second
                 str/trim
                 parse-regex)))
          handle-nums))))

(defn parse-item-selection
  "Parse RF parameters for describing an item in a select list. Return vector, first item a fn for selecting, the second item the param to that fn"
  [^String item-descriptor op]
  (if (or (nil? item-descriptor) (empty? item-descriptor) (= "nil" (.toLowerCase item-descriptor)))
    [wd/select-by-index 0] ; if nothing, default to picking first item in list
    (let [descriptions (remove empty? (str/split item-descriptor #"(?<!\\),"))
          desc-map (-> (zipmap
                        (for [desc descriptions]
                          (-> (re-find #"^([^=]+)=" desc)
                              second
                              str/trim
                              .toLowerCase
                              (str/replace "_" "-")
                              keyword))
                        (for [desc descriptions]
                          (-> (re-find #"^[^=]+=(.*?)$" desc)
                              second
                              str/trim
                              parse-regex)))
                       handle-nums)]
      (condp = op
          :select (cond
                   (contains? desc-map :index) [wd/select-by-index (Integer. (:index desc-map))] ; human-friendly index, 1-based
                   (contains? desc-map :value) [wd/select-by-value (:value desc-map)]
                   (contains? desc-map :text)  [wd/select-by-text (:text desc-map)]
                   :else (throw (IllegalArgumentException.
                                 (str "When selecting an element from a select list, "
                                      "you may only use the 'index', 'value' or 'text' "
                                      "attributes."))))
          :deselect (cond
                     (contains? desc-map :index) [wd/deselect-by-index (Integer. (:index desc-map))] ; human-friendly index, 1-based
                     (contains? desc-map :value) [wd/deselect-by-value (:value desc-map)]
                     (contains? desc-map :text)  [wd/deselect-by-text (:text desc-map)]
                     :else (throw (IllegalArgumentException.
                                   (str "When selecting an element from a select list, "
                                        "you may only use the 'index', 'value' or 'text' "
                                        "attributes."))))))))