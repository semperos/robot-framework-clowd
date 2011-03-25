;; Parsing and Emitting Values
;;
;; This namespace is used to define methods for the multimethod `parse-value`
;; and to extend the `ValueTypeElem` protocol
;;
;; This is part of the core namespace to make it easier to use the server
;; (have a single namespace require).

(ns robot-framework-clowd.xml-rpc.value
  (:use [clj-webdriver.util :only [first-60 elim-breaks when-attr]]
        [necessary-evil value xml-utils]))

(extend-protocol ValueTypeElem
  org.openqa.selenium.WebDriver
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.chrome.ChromeDriver
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.support.events.EventFiringWebDriver
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.iphone.IPhoneDriver
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.firefox.FirefoxDriver
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.htmlunit.HtmlUnitDriver
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.ie.InternetExplorerDriver
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.remote.RemoteWebDriver
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.WebElement
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.RenderedWebElement
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.firefox.FirefoxWebElement
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.htmlunit.HtmlUnitWebElement
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.ie.InternetExplorerElement
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.remote.RemoteWebElement
  (value-type-elem [this] (elem :string [(str this)]))

  org.openqa.selenium.remote.RenderedRemoteWebElement
  (value-type-elem [this] (elem :string [(str this)]))

  com.google.common.collect.Maps$TransformedEntriesMap
  (value-type-elem [this] (elem :string [(str this)])))

(comment

  (defn- webdriver-to-str [driver]
    (let [caps (.getCapabilities driver)]
      (str "#(" "Title: "            (.getTitle driver) ", "
           "URL: "                   (first-60 (.getCurrentUrl driver)) ", "
           "Browser: "               (.getBrowserName caps) ", "
           "Version: "               (.getVersion caps) ", "
           "JS Enabled: "            (.isJavascriptEnabled caps) ", "
           "Native Events Enabled: " (boolean (re-find #"nativeEvents=true" (.toString caps))) ", "
           "Object: "                driver ")")))

  (defn- webelement-to-str [webelement]
    (let [tag-name   (.getTagName webelement)
          text       (.getText webelement)
          id         (.getAttribute webelement "id")
          class-name (.getAttribute webelement "class")
          value      (.getAttribute webelement "value")
          href       (.getAttribute webelement "href")
          src        (.getAttribute webelement "src")
          obj        webelement]
      (str "#("
           (when-attr tag-name
                      (str "Tag: "    tag-name ", "))
           (when-attr text
                      (str "Text: "   (-> text elim-breaks first-60) ", "))
           (when-attr id
                      (str "Id: "     id ", "))
           (when-attr class-name
                      (str "Class: "  class-name ", "))
           (when-attr value
                      (str "Value: "  value ", "))
           (when-attr href
                      (str "Href: "   href ", "))
           (when-attr src
                      (str "Source: " src ", "))
           "Object: "                  webelement ")")))
)