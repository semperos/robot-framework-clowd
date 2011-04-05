(in-ns 'robot-framework-clowd.keywords)

;; ## Actions

(defn click-image
  [descriptor]
  (browser-> :img descriptor wd/click))