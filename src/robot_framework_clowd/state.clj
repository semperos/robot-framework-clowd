;; # State
;;
;; This namespace holds all "global state" for this program. Currently,
;; this consists of only one atom, `rf-browser`, used to hold the
;; currently-opened browser instance. While this makes the entire
;; library much less functional, it is a necessity as a "glue API"
;; between the automated testing framework RobotFramework and the
;; Clojure wrapper around Selenium-WebDriver called clj-webdriver.

(ns robot-framework-clowd.state)

;; Global instance of currently-opened browser
(def rf-browser (atom nil))