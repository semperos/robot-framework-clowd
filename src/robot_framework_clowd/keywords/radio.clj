(in-ns 'robot-framework-clowd.keywords)

;; ## Actions

(defn select-radio-button
  "Select the specified radio button"
  [descriptor]
  (browser-> :radio descriptor wd/select))

;; ## Conditions

(defn radio-button-should-be-selected
  "Verify that the specified radio button form element is selected"
  [descriptor]
  (throw-not-selected :radio descriptor))

(defn radio-button-should-not-be-selected
  "Verify that the specified radio button form element is selected"
  [descriptor]
  (throw-selected :radio descriptor))