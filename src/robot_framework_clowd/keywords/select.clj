(in-ns 'robot-framework-clowd.keywords)

;; ## Actions

(defn select-list-item
  [select-list-descriptor item-descriptor]
  (let [select-list (browser-> :select select-list-descriptor)
        [select-fn param] (parse-item-selection item-descriptor :select)]
    (select-fn select-list param)))

(defn deselect-list-item
  [select-list-descriptor item-descriptor]
  (let [select-list (browser-> :select select-list-descriptor)
        [select-fn param] (parse-item-selection item-descriptor :deselect)]
    (select-fn select-list param)))

(defn select-all-list-items
  [descriptor]
  (let [select-list (browser-> :select descriptor)]
    (wd/select-all select-list)))

(defn deselect-all-list-items
  [descriptor]
  (let [select-list (browser-> :select descriptor)]
    (wd/deselect-all select-list)))

(def clear-list-items deselect-all-list-items)

;; ## Conditions

(defn item-should-be-selected
  [select-list-descriptor item-descriptor]
  (throw-not-selected [:select select-list-descriptor,
                       :option item-descriptor] nil))

(defn item-should-not-be-selected
  [select-list-descriptor item-descriptor]
  (throw-selected [:select select-list-descriptor,
                       :option item-descriptor] nil))