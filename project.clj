(defproject robot-framework-clowd "0.0.1-SNAPSHOT"
  :description "RobotFramework keyword library for web testing written in Clojure leveraging Selenium-WebDriver"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [necessary-evil "1.1.0"]
                 [robot-remote-server "0.2.3"]
                 [org.seleniumhq.selenium/selenium-server "2.0b2"]
                 ;;[clj-webdriver "0.2.1"] using /checkouts currently
                 ]
  :dev-dependencies [[swank-clojure "1.3.0-SNAPSHOT"]
                     [marginalia "0.5.0"]]
  :main robot-framework-clowd.keywords)