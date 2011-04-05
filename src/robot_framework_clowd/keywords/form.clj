(in-ns 'robot-framework-clowd.keywords)

(defn form-should-contain-submit-button
  [form-descriptor button-descriptor]
  (throw-not-exists [:form form-descriptor,
                     :input (str button-descriptor ",type=submit")] nil))

(defn form-should-not-contain-submit-button
  [form-descriptor button-descriptor]
  (throw-exists [:form form-descriptor,
                 :input (str button-descriptor ",type=submit")] nil))

(defn form-should-contain-reset-button
  [form-descriptor button-descriptor]
  (throw-not-exists [:form form-descriptor,
                     :input (str button-descriptor ",type=reset")] nil))

(defn form-should-not-contain-reset-button
  [form-descriptor button-descriptor]
  (throw-exists [:form form-descriptor,
                 :input (str button-descriptor ",type=reset")] nil))

(defn form-should-contain-checkbox
  [form-descriptor checkbox-descriptor]
  (throw-not-exists [:form form-descriptor,
                     :input (str checkbox-descriptor ",type=checkbox")] nil))

(defn form-should-not-contain-checkbox
  [form-descriptor checkbox-descriptor]
  (throw-exists [:form form-descriptor,
                 :input (str checkbox-descriptor ",type=checkbox")] nil))

(defn form-should-contain-element
  [form-descriptor element-descriptor]
  (throw-not-exists [:form form-descriptor,
                     :* element-descriptor] nil))

(defn form-should-not-contain-element
  [form-descriptor element-descriptor]
  (throw-exists [:form form-descriptor,
                 :* element-descriptor] nil))

(defn form-should-contain-filefield
  [form-descriptor filefield-descriptor]
  (throw-not-exists [:form form-descriptor,
                     :input (str filefield-descriptor
                                 ",type=file")] nil))

(defn form-should-not-contain-filefield
  [form-descriptor filefield-descriptor]
  (throw-exists [:form form-descriptor,
                 :input (str filefield-descriptor
                             ",type=file")] nil))

(defn form-should-contain-radio-button
  [form-descriptor radio-button-descriptor]
  (throw-not-exists [:form form-descriptor,
                     :input (str radio-button-descriptor
                                 ",type=radio")] nil))

(defn form-should-not-contain-radio-button
  [form-descriptor radio-button-descriptor]
  (throw-exists [:form form-descriptor,
                 :input (str radio-button-descriptor
                             ",type=radio")] nil))

(defn form-should-contain-select-list
  [form-descriptor select-list-descriptor]
  (throw-not-exists [:form form-descriptor,
                     :select select-list-descriptor] nil))

(defn form-should-not-contain-select-list
  [form-descriptor select-list-descriptor]
  (throw-exists [:form form-descriptor,
                 :select select-list-descriptor] nil))

(defn form-should-contain-textarea
  [form-descriptor textarea-descriptor]
  (throw-not-exists [:form form-descriptor,
                     :textarea textarea-descriptor] nil))

(defn form-should-not-contain-textarea
  [form-descriptor textarea-descriptor]
  (throw-exists [:form form-descriptor,
                 :textarea textarea-descriptor] nil))

(defn form-should-contain-textfield
  [form-descriptor textfield-descriptor]
  (throw-not-exists [:form form-descriptor
                     :input (str textfield-descriptor
                                 ",type=text")] nil))

(defn form-should-not-contain-textfield
  [form-descriptor textfield-descriptor]
  (throw-exists [:form form-descriptor
                 :input (str textfield-descriptor
                             ",type=text")] nil))