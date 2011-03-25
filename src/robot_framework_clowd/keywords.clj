(ns robot-framework-clowd.keywords
  (:require [clj-webdriver.core :as wd] :reload-all
            [clj-webdriver.firefox :as firefox])
  (:use [robot-framework-clowd core] :reload
        robot-remote-server.core)
  (:import javax.swing.JOptionPane)
  (:gen-class :main true))

(defn open-dialog
  "Open a JOptionPane, just testing things"
  []
  (JOptionPane/showMessageDialog
    nil "Hello, Clojure World!" "Greeting"
    JOptionPane/INFORMATION_MESSAGE))

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
      (server-start! (init-handler false))))     ; for handling WebDriver classes