(in-ns 'robot-framework-clowd.keywords)

(defn capture-screenshot
  "Take a screenshot of the entire screen"
  []
  (let [size (.getScreenSize (Toolkit/getDefaultToolkit))
        rect (Rectangle. 0 0 (.width size) (.height size))
        robot (Robot.)
        img (.createScreenCapture robot rect)
        path (str (rand-int 100000) "_screenshot.png")
        file (java.io.File. path)]
    (do
      (ImageIO/write img "png" file))))

(defn move-mouse-to-position
  [x-coordinate y-coordinate]
  (let [robot (Robot.)]
    (.mouseMove robot (Integer. x-coordinate) (Integer. y-coordinate))))

(defn press-left-mouse-button
  []
  (let [robot (Robot.)]
    (.mousePress robot InputEvent/BUTTON1_MASK)))

(defn press-middle-mouse-button
  []
  (let [robot (Robot.)]
    (.mousePress robot InputEvent/BUTTON2_MASK)))

(defn press-right-mouse-button
  []
  (let [robot (Robot.)]
    (.mousePress robot InputEvent/BUTTON3_MASK)))

(defn release-left-mouse-button
  []
  (let [robot (Robot.)]
    (.mouseRelease robot InputEvent/BUTTON1_MASK)))

(defn release-middle-mouse-button
  []
  (let [robot (Robot.)]
    (.mouseRelease robot InputEvent/BUTTON2_MASK)))

(defn release-right-mouse-button
  []
  (let [robot (Robot.)]
    (.mouseRelease robot InputEvent/BUTTON3_MASK)))

(defn click-left-mouse-button
  []
  (let [robot (Robot.)]
    (do
      (.mousePress robot InputEvent/BUTTON1_MASK)
      (.mouseRelease robot InputEvent/BUTTON1_MASK))))

(defn click-middle-mouse-button
  []
  (let [robot (Robot.)]
    (do
      (.mousePress robot InputEvent/BUTTON2_MASK)
      (.mouseRelease robot InputEvent/BUTTON2_MASK))))

(defn click-right-mouse-button
  []
  (let [robot (Robot.)]
    (do
      (.mousePress robot InputEvent/BUTTON3_MASK)
      (.mouseRelease robot InputEvent/BUTTON3_MASK))))