(ns robot-framework-clowd.parser
  (:require [clojure.string :as str]))

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

(defn parse-descriptor
  "Given the arguments to a keyword from RobotFramework, parse the arguments string and return a data structure needed by clj-webdriver"
  [^String descriptor]
  (if (or (nil? descriptor) (empty? descriptor) (= "nil" (.toLowerCase descriptor)))
    nil
    (let [descriptions (str/split descriptor #"(?<!\\),")]
      (-> (zipmap
           (for [desc descriptions]
             (-> (re-find #"^([^=]+)=" desc)
                 second
                 .toLowerCase
                 (str/replace "_" "-")
                 keyword))
           (for [desc descriptions]
             (-> (re-find #"^[^=]+=(.*?)$" desc)
                 second
                 parse-regex)))
          handle-nums))))