(in-ns 'robot-framework-clowd.keywords)

(defn capture-screenshot []
  (let [size (.getScreenSize (Toolkit/getDefaultToolkit))
        rect (Rectangle. 0 0 (.width size) (.height size))
        robot (Robot.)
        img (.createScreenCapture robot rect)
        path (str (rand-int 100000) "_screenshot.png")
        file (java.io.File. path)]
    (do
      (ImageIO/write img "png" file))))