(in-ns 'robot-framework-clowd.keywords)

;; ## Actions

(defn get-element-attribute
  "For the element specified by `descriptor`, return the value of the attribute `attr`"
  [descriptor attr]
  (browser-> :* descriptor (wd/attribute attr)))

(defn get-element-text
  "Return the visible text for the specified element"
  [descriptor]
  (browser-> :* descriptor wd/text))

(defn click-element
  "Click the specified element"
  [descriptor]
  (browser-> :* descriptor wd/click))

(defn focus
  "Set focus on the specified element (usually keyboard cursor)"
  [descriptor]
  (browser-> :* descriptor wd/focus))

(defn type-text
  "Type the given string 'onto' the specified element"
  [descriptor text]
  (browser-> :* descriptor wd/input-text))

;; ## Conditions

(defn element-should-be-visible
  "Verify that the specified element is visible"
  [descriptor]
  (throw-fn-false wd/visible? :* descriptor
                  (str "The HTML element described by the attributes \""
                       descriptor "\" "
                       "is not visible on the page: "
                       (wd/page-source @rf-browser))))

(defn element-should-not-be-visible
  "Verify that the specified element is visible"
  [descriptor]
  (throw-fn-true wd/visible? :* descriptor
                  (str "The HTML element described by the attributes \""
                       descriptor "\" "
                       "is erroneously visible on the page: "
                       (wd/page-source @rf-browser))))

(defn element-should-be-enabled
  "Verify that the specified element is enabled"
  [descriptor]
  (throw-fn-false wd/enabled? :* descriptor
                  (str "The HTML element described by the attributes \""
                       descriptor "\" "
                       "is not enabled on the page: "
                       (wd/page-source @rf-browser))))

(defn element-should-not-be-enabled
  "Verify that the specified element is enabled"
  [descriptor]
  (throw-fn-true wd/enabled? :* descriptor
                  (str "The HTML element described by the attributes \""
                       descriptor "\" "
                       "is erroneously enabled on the page: "
                       (wd/page-source @rf-browser))))

(defn element-should-be-present
  "Verify that the specified element is present (i.e. both exists and is visible)"
  [descriptor]
  (throw-fn-false wd/present? :* descriptor
                  (str "The HTML element described by the attributes \""
                       descriptor "\" "
                       "does not exist or is not visible on the page: "
                       (wd/page-source @rf-browser))))

(defn element-should-not-be-present
  "Verify that the specified element is present (i.e. both exists and is visible)"
  [descriptor]
  (throw-fn-true wd/present? :* descriptor
                  (str "The HTML element described by the attributes \""
                       descriptor "\" "
                       "erroneously exists and/or is visible on the page: "
                       (wd/page-source @rf-browser))))

(defn element-text-should-contain
  "Verify that the text of hte specified element contains `text`"
  [descriptor text]
  (let [element-text (browser-> :* descriptor wd/text)]
    (throw-not-str-contains element-text text)))

(defn element-text-should-not-contain
  "Verify that the text of hte specified element contains `text`"
  [descriptor text]
  (let [element-text (browser-> :* descriptor wd/text)]
    (throw-str-contains element-text text)))