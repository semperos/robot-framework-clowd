(in-ns 'robot-framework-clowd.keywords)

;; ## Actions

(defn select-checkbox
  "Select the specified checkbox"
  [descriptor]
  (browser-> :checkbox descriptor wd/select))

(defn deselect-checkbox
  "Deselect the specified checkbox"
  [descriptor]
  (browser-> :checkbox descriptor wd/deselect))

(defn toggle-checkbox
  "If a checkbox is selected, make it deselected, and vice versa"
  [descriptor]
  (browser-> :checkbox descriptor wd/toggle))

;; ## Conditions

(defn checkbox-should-be-selected
  "Verify that the specified checkbox is selected"
  [descriptor]
  (throw-not-selected :checkbox descriptor))

(defn checkbox-should-not-be-selected
  "Verify that the specified checkbox is not selected"
  [descriptor]
  (throw-selected :checkbox descriptor))