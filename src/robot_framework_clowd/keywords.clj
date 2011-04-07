(ns robot-framework-clowd.keywords
  (:require [clj-webdriver.core :as wd] :reload-all
            [clj-webdriver.firefox :as firefox])
  (:use robot-framework-clowd.state
        [robot-framework-clowd core parser] :reload
        robot-remote-server.core)
  (:import javax.swing.JOptionPane
           java.awt.Robot
           java.awt.Toolkit
           java.awt.Dimension
           java.awt.Rectangle
           java.awt.image.BufferedImage
           javax.imageio.ImageIO
           java.awt.event.InputEvent)
  (:gen-class :main true))

(defn open-dialog
  "Open a JOptionPane, just testing things"
  []
  (JOptionPane/showMessageDialog
    nil "Hello, Clojure World!" "Greeting"
    JOptionPane/INFORMATION_MESSAGE))

;; A single namespace is used because the remote server's
;; `init-handler` macro looks in a single namespace for all
;; of the functions defined as Robot Framework keywords.
(load "keywords/area")
(load "keywords/browser")
(load "keywords/button")
(load "keywords/checkbox")
(load "keywords/element")
(load "keywords/file_field")
(load "keywords/form")
(load "keywords/image")
(load "keywords/link")
(load "keywords/list")
(load "keywords/native")
(load "keywords/page")
(load "keywords/radio")
(load "keywords/select")
(load "keywords/table")
(load "keywords/text_field")

(defn -main
  []
  (do (use 'robot-remote-server.core
           'robot-framework-clowd.xml-rpc.value :reload-all) ; value ns extends protocols
      (server-start! (init-handler))))     ; for handling WebDriver classes