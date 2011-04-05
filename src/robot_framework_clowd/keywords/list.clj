(in-ns 'robot-framework-clowd.keywords)

(defn get-ordered-list-items
  [ul-descriptor]
  (->> [:ol (parse-descriptor ul-descriptor), :li {}]
       (wd/find-them @rf-browser)
       (map wd/text)
       (interpose ",,,")
       (apply str)))

(defn get-unordered-list-items
  [ul-descriptor]
  (->> [:ul (parse-descriptor ul-descriptor), :li {}]
       (wd/find-them @rf-browser)
       (map wd/text)
       (interpose ",,,")
       (apply str)))