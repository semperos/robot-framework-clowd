(in-ns 'robot-framework-clowd.keywords)

;; ## Actions

(defn get-page-source
  []
  (wd/page-source @rf-browser))

(defn get-page-text
  []
  (browser-> :html nil wd/text))

(defn get-page-status
  []
  (wd/execute-script @rf-browser "return window.status;"))

(defn get-title
  []
  (wd/title @rf-browser))

;; ## Conditions

(defn page-should-contain
  "Verify that the page's text contains a certain value"
  [text]
  (throw-not-str-contains (get-page-text) text))

(defn page-should-not-contain
  "Verify that the page's text contains a certain value"
  [text]
  (throw-str-contains (get-page-text) text))

;; ### HTML Elements

(defn page-should-contain-area
  "Verify area tag exists on the page"
  [descriptor]
  (throw-not-exists :area descriptor))

(defn page-should-not-contain-area
  "Verify area tag does not exist on the page"
  [descriptor]
  (throw-exists :area descriptor))

(defn page-should-contain-button
  "Verify button (either `<button>` tag or `<input>` of type submit, reset, image or button) exists on the page"
  [descriptor]
  (throw-not-exists :button* descriptor))

(defn page-should-not-contain-button
  "Verify button (either `<button>` tag or `<input>` of type submit, reset, image or button) does not exist on the page"
  [descriptor]
  (throw-exists :button* descriptor))

(defn page-should-contain-checkbox
  "Verify checkbox form element exists on the page"
  [descriptor]
  (throw-not-exists :checkbox descriptor))

(defn page-should-not-contain-checkbox
  "Verify checkbox form element does not exist on the page"
  [descriptor]
  (throw-exists :checkbox descriptor))

(defn page-should-contain-element
  "Verify that the HTML element exists on the page"
  [descriptor]
  (throw-not-exists :* descriptor))

(defn page-should-not-contain-element
  "Verify that the HTML element does not exist on the page"
  [descriptor]
  (throw-exists :* descriptor))

(defn page-should-contain-form
  "Verify that the form exists on the page"
  [descriptor]
  (throw-not-exists :form descriptor))

(defn page-should-not-contain-form
  "Verify that the form does not exist on the page"
  [descriptor]
  (throw-exists :form descriptor))

(defn page-should-contain-image
  "Verify that the image exists on the page"
  [descriptor]
  (throw-not-exists :img descriptor))

(defn page-should-not-contain-image
  "Verify that the image does not exist on the page"
  [descriptor]
  (throw-exists :img descriptor))

(defn page-should-contain-link
  "Verify that the link (`<a>` tag) exists on the page"
  [descriptor]
  (throw-not-exists :a descriptor))

(defn page-should-not-contain-link
  "Verify that the link (`<a>` tag) does not exist on the page"
  [descriptor]
  (throw-exists :a descriptor))

(defn page-should-contain-ordered-list
  "Verify that the ordered list exists on the page"
  [descriptor]
  (throw-not-exists :ol descriptor))

(defn page-should-not-contain-ordered-list
  "Verify that the ordered list does not exist on the page"
  [descriptor]
  (throw-exists :ol descriptor))

(defn page-should-contain-unordered-list
  "Verify that the unordered list exists on the page"
  [descriptor]
  (throw-not-exists :ul descriptor))

(defn page-should-not-contain-unordered-list
  "Verify that the unordered list does not exist on the page"
  [descriptor]
  (throw-exists :ul descriptor))

(defn page-should-contain-radio-button
  "Verify that the radio button form element exists on the page"
  [descriptor]
  (throw-not-exists :radio descriptor))

(defn page-should-not-contain-radio-button
  "Verify that the radio button form element does not exist on the page"
  [descriptor]
  (throw-exists :radio descriptor))

(defn page-should-contain-select-list
  "Verify that the select list exists on the page"
  [descriptor]
  (throw-not-exists :select descriptor))

(defn page-should-not-contain-select-list
  "Verify that the select list form element does not exist on the page"
  [descriptor]
  (throw-exists :select descriptor))

(defn page-should-contain-textfield
  "Verify that the textfield form element exists on the page"
  [descriptor]
  (throw-not-exists :textfield descriptor))

(defn page-should-not-contain-textfield
  "Verify that the textfield form element does not exist on the page"
  [descriptor]
  (throw-exists :textfield descriptor))

(defn title-should-be
  "Verify that the page title matches the given `title`"
  [title]
  (throw-not= (wd/title @rf-browser) title))

(defn title-should-contain
  "Verify that the page title contains the text in `title`"
  [title]
  (throw-not-str-contains (wd/title @rf-browser) title))