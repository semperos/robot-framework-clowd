(ns robot-framework-clowd.core
  (:use [robot-framework-clowd parser])
  (:require [clj-webdriver.core :as wd]))

(def rf-browser (atom nil))

;; purposefully non-functional macro; relies on rf-browser,
;; which is used throughout the entirety of this library
(defmacro browser->
  "Convenience macro. Perform the `actions` on the element matched by `tag` and `descriptor`, parsing `descriptor` according to the syntax of our RobotFramework tests"
  [tag descriptor & actions]
  `(-> @rf-browser
       (wd/find-it (keyword ~tag) (parse-descriptor ~descriptor))
       ~@actions))

;; Convenience macros for performing actions and throwing consistent exceptions.
;; Read these by inserting the word "if" after the word "throw", e.g.
;; "throw if equals", "throw if contains", "throw if matches", etc.
(defmacro throw=
  "Throw an exception if items are equal erroneously, return true otherwise"
  [expr1 expr2 & messages]
  `(let [alpha# ~expr1
         beta#  ~expr2]
     (if (= alpha# beta#)
       (throw (RuntimeException.
               (str "The item \"" alpha# "\" erroneously equals \"" beta# "\"\n"
                    ~@messages)))
       true)))

(defmacro throw-not=
  "Throw an exception if items are not equal, return true otherwise"
  [expr1 expr2 & messages]
  `(let [alpha# ~expr1
         beta#  ~expr2]
     (if-not (= alpha# beta#)
       (throw (RuntimeException.
               (str "The item \"" alpha# "\" does not equal \"" beta# "\"\n"
                    ~@messages)))
       true)))

(defmacro throw-str-contains
  "Throw an exception if `s1` contains `s2` erroneously, return true otherwise"
  [s1 s2 & messages]
  `(let [alpha# ~s1
         beta#  ~s2]
     (if (.contains alpha# beta#)
       (throw (RuntimeException.
               (str "The text \"" beta# "\" is erroneously contained within \"" alpha# "\"\n"
                    ~@messages)))
       true)))

(defmacro throw-not-str-contains
  "Throw an exception if `str1` contains `str2` erroneously, return true otherwise"
  [s1 s2 & messages]
  `(let [alpha# ~s1
         beta#  ~s2]
     (if-not (.contains alpha# beta#)
       (throw (RuntimeException.
               (str "The text \"" beta# "\" is not contained within \"" alpha# "\"\n"
                    ~@messages)))
       true)))

(defmacro throw-match
  "Throw an exception if `s-re` as a regular expression erroneously matches `s`, otherwise return true"
  [s-re s & messages]
  `(let [alpha# (re-pattern ~s-re)
         beta#  ~s]
     (if (re-find alpha# beta#)
       (throw (RuntimeException.
               (str "The regular expression /" alpha#
                    "/ erroneously matches \"" beta# "\""
                    ~@messages)))
       true)))

(defmacro throw-not-match
  "Throw an exception if `s-re` as a regular expression does not match `s`, otherwise return true"
  [s-re s & messages]
  `(let [alpha# (re-pattern ~s-re)
         beta#  ~s]
     (if-not (re-find alpha# beta#)
       (throw (RuntimeException.
               (str "The regular expression /" alpha#
                    "/ does not match \"" beta# "\""
                    ~@messages)))
       true)))

(defmacro throw-exists
  "Throw an exception if the element matched by `tag` and `descriptor` erroneously exists on the given web page"
  [tag descriptor]
  `(if (browser-> ~tag ~descriptor wd/exists?)
     (throw (RuntimeException.
             (str "The HTML element <" (name ~tag) "> " ; though RF always sends strings
                  "described by the attributes \"" ~descriptor "\" "
                  "erroneously exists on the page: "
                  (wd/page-source @rf-browser))))
     true))

(defmacro throw-not-exists
  "Throw an exception if the element matched by `tag` and `descriptor` erroneously exists on the given web page"
  [tag descriptor]
  `(if-not (browser-> ~tag ~descriptor wd/exists?)
     (throw (RuntimeException.
             (str "The HTML element <" (name ~tag) "> " ; though RF always sends strings
                  "described by the attributes \"" ~descriptor "\" "
                  "does not exist on the page: "
                  (wd/page-source @rf-browser))))
     true))