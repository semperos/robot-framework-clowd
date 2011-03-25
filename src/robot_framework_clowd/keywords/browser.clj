(in-ns 'robot-framework-clowd.keywords)

;; Stateful fn's to get things rolling
(defn open-browser
  "Start a new browser session (native events disabled by default)"
  [^String browser]
  (let [browser (keyword (.toLowerCase browser))]
    (if (= browser :firefox)
      (let [profile (doto (firefox/new-profile)
                      (firefox/enable-native-events false))] ; native events are buggy
        (reset! rf-browser (wd/new-driver browser profile))
        @rf-browser)
      (do
        (reset! rf-browser (wd/new-driver browser))
        @rf-browser))))

(defn open-browser-with-native-events
  "Start a new browser session and explicitly enable native events (buggy)"
  [^String browser]
  (let [browser (keyword (.toLowerCase browser))]
    (reset! rf-browser (wd/new-driver browser))
    @rf-browser))

(defn start-browser
  "Start a new browser session and navigate to an intial URL.

   Note: Native events are disabled for this keyword. Use \"Open Browser With Native Events\" and the \"Go To\" keywords to simulate this keyword's behavior with native events enabled.)"
  [^String browser ^String url]
  (open-browser browser)
  (wd/to @rf-browser url)
  @rf-browser)

(defn close-browser
  "Close the current browser"
  []
  (wd/close @rf-browser))

(defn quit-browser
  "Quit the browser program"
  []
  (wd/quit @rf-browser)
  nil)

(defn get-url
  "Retrieve the URL of the current page"
  []
  (wd/current-url @rf-browser)
  @rf-browser)

(defn go-to
  "Navigate to the specified URL"
  [^String url]
  (wd/to @rf-browser url)
  @rf-browser)

(defn go-back
  "Go backward in browser history"
  []
  (wd/back @rf-browser)
  @rf-browser)

(defn go-forward
  "Go forward in browser history"
  []
  (wd/forward @rf-browser)
  @rf-browser)

(defn refresh
  "Refresh the current page"
  []
  (wd/refresh @rf-browser)
  @rf-browser)

(defn execute-javascript
  [js & js-args]
  (let [my-fn (partial wd/execute-script @rf-browser js)]
    (apply my-fn js-args)))

(defn switch-to-other-window
  []
  (if (> 2 (count (wd/window-handles* @rf-browser))) ; window-handles* is more
    (throw (RuntimeException.                        ; efficient for just counting
            (str "You cannot use this keyword when more than 2 windows are open; "
                 "you must use one of the other \"Switch To...\" keywords.")))
    (wd/switch-to-other-window @rf-browser)))

(defn switch-to-window
  [^String descriptor]
  (wd/switch-to-window @rf-browser (browser-> :window descriptor)))

(defn get-window-count
  []
  (count (wd/window-handles* @rf-browser)))

(defn url-should-be
  [a-url]
  (throw-not= (wd/current-url @rf-browser) a-url))

(defn url-should-not-be
  [a-url]
  (throw= (wd/current-url @rf-browser) a-url))

(defn url-should-contain
  [text]
  (throw-not-str-contains (wd/current-url @rf-browser) text))

(defn url-should-not-contain
  [text]
  (throw-str-contains (wd/current-url @rf-browser) text))

(defn url-should-match-regexp
  [regexp]
  (throw-not-match regexp (wd/current-url @rf-browser)))

(defn url-should-not-match-regexp
  [regexp]
  (throw-match regexp (wd/current-url @rf-browser)))

;; TODO: get-all-cookies, get-cookie, delete-all-cookies, delete-cookie