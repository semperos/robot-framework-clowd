(in-ns 'robot-framework-clowd.keywords)

(defn click-link
  "Click a link that matches the supplied descriptor"
  [descriptor]
  (browser-> :a descriptor wd/click)
  @rf-browser)