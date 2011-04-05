(in-ns 'robot-framework-clowd.keywords)

(defn get-ordered-list-items
  "For the ordered list described by `ol-descriptor`, retrieve the text of all items and separate them by three commas `,,,`"
  [ol-descriptor]
  (->> [:ol (parse-descriptor ol-descriptor), :li {}]
       (wd/find-them @rf-browser)
       (map wd/text)
       (interpose ",,,")
       (apply str)))

(defn get-unordered-list-items
  "For the unordered list described by `ul-descriptor`, retrieve the text of all items and separate them by three commas `,,,`"
  [ul-descriptor]
  (->> [:ul (parse-descriptor ul-descriptor), :li {}]
       (wd/find-them @rf-browser)
       (map wd/text)
       (interpose ",,,")
       (apply str)))