(in-ns 'robot-framework-clowd.keywords)

;; ## Actions

(defn get-table-cell*
  [descriptor row col]
  (wd/find-it @rf-browser [:table (parse-descriptor descriptor),
                           :row (dec (Integer. row))
                           :col (dec (Integer. col))]))

(defn get-table-cell
  [descriptor row col]
  (wd/text (get-table-cell* descriptor row col)))

;; ## Conditions

(defn table-cell-should-contain
  "Verify that the given table cell contains the text `text`"
  [descriptor row col text]
  (let [cell (get-table-cell descriptor row col)]
    (throw-not-str-contains (wd/text cell) text)))

(defn table-cell-should-not-contain
  "Verify that the given table cell does not contain the text `text`"
  [descriptor row col text]
  (let [cell (get-table-cell descriptor row col)]
    (throw-str-contains (wd/text cell) text)))

(defn table-row-should-contain
  [descriptor row text]
  (if-let [target-row (wd/find-it @rf-browser [:table (parse-descriptor descriptor),
                                               :tr {:index (dec row)}])]
    (throw-not-str-contains (wd/text target-row) text)
    (throw (RuntimeException.
            (str "No table found with descriptor "
                 descriptor)))))

(defn table-row-should-not-contain
  [descriptor row text]
  (if-let [target-row (wd/find-it @rf-browser [:table (parse-descriptor descriptor),
                                               :tr {:index (dec row)}])]
    (throw-str-contains (wd/text target-row) text)
    (throw (RuntimeException.
            (str "No table found with descriptor "
                 descriptor)))))

(defn table-column-should-contain
  [descriptor col text]
  (let [row-cnt (count
                 (wd/find-them @rf-browser
                               [:table (parse-descriptor descriptor),
                                :tr {}]))
        row-range (range 1 (inc row-cnt))
        col-text (apply str (for [row row-range]
                              (get-table-cell descriptor row col)))]
    (throw-not-str-contains col-text text)))

(defn table-column-should-not-contain
  [descriptor col text]
  (let [row-cnt (count
                 (wd/find-them @rf-browser
                               [:table (parse-descriptor descriptor),
                                :tr {}]))
        row-range (range 1 (inc row-cnt))
        col-text (apply str (for [row row-range]
                              (get-table-cell descriptor row col)))]
    (throw-str-contains col-text text)))

(defn table-header-should-contain
  [descriptor text]
  (if-let [thead (wd/find-it @rf-browser [:table (parse-descriptor descriptor),
                                          :thead {}])]
    (throw-not-str-contains (wd/text thead) text)
    (if-let [target (wd/find-it @rf-browser [:table (parse-descriptor descriptor),
                                             :tr {}])]
      (throw-not-str-contains (wd/text target) text)
      (throw (RuntimeException.
              (str "No table found with descriptor "
                   descriptor))))))

(defn table-header-should-not-contain
  [descriptor text]
  (if-let [thead (wd/find-it @rf-browser [:table (parse-descriptor descriptor),
                                          :thead {}])]
    (throw-str-contains (wd/text thead) text)
    (if-let [target (wd/find-it @rf-browser [:table (parse-descriptor descriptor),
                                             :tr {}])]
      (throw-str-contains (wd/text target) text)
      (throw (RuntimeException.
              (str "No table found with descriptor "
                   descriptor))))))

(defn table-should-contain
  [descriptor text]
  (if-let [target-table (browser-> :table descriptor)]
    (throw-not-str-contains (wd/text target-table) text)
    (throw (RuntimeException.
            (str "No table found with descriptor "
                 descriptor)))))

(defn table-should-not-contain
  [descriptor text]
  (if-let [target-table (browser-> :table descriptor)]
    (throw-str-contains (wd/text target-table) text)
    (throw (RuntimeException.
            (str "No table found with descriptor "
                 descriptor)))))