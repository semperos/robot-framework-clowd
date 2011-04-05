(in-ns 'robot-framework-clowd.keywords)

;; ## Actions

(defn choose-file
  "Provide the path for a local file to the specified filefield form element"
  [descriptor local-file-path]
  (browser-> :filefield descriptor (wd/send-keys (clean-file-path local-file-path))))

(defn get-filefield-path
  "Get the path present in the specified filefield form element"
  [descriptor]
  (browser-> :filefield descriptor wd/value))