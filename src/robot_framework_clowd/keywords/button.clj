(in-ns 'robot-framework-clowd.keywords)

(defn click-button
  "Click the specified button"
  [descriptor]
  (browser-> :button* descriptor wd/click)
  @rf-browser)