(in-ns 'robot-framework-clowd.keywords)

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
  "Verify button (either `<button>` tag or `<input>` of type submit, reset, image or button) does not on the page"
  [descriptor]
  (throw-exists :button* descriptor))