# RobotFramework Clowd

Clowd is an acronym that stands for CLOjure WebDriver. This is a RobotFramework keyword library for functional web testing that leverages Selenium-WebDriver under the hood. The [clj-webdriver][clj-webdriver-github] library provides the Clojure API around Selenium-WebDriver, and the [robot-remote-server][rrs-github] project provides a Clojure implementation of RobotFramework's remote server, which allows RobotFramework to use the Clojure keywords in this library.

## Usage

This library has not been implemented yet, but here are the planned steps.

This library will provide a `main` function as an entry-point for an executable jar. This `main` function will start up the Clojure remote server and feed it the keywords from this library. So you will be able to start the server like this:

    java -jar robot-framework-clowd-x.x.x.jar

In your RobotFramework tests, you need to include the remote library in your `*** Settings ***` section like so (make sure to use the right port, 8270 is the default):

    Library         Remote  http://localhost:8270

Once you've added the remote library in your RobotFramework tests and the server is running, you can run your RobotFramework tests per usual. For Clojure developers new to RobotFramework, the easiest point of entry might be to download the standalone RobotFramework jar and run your tests as follows:

    java -jar robotframework-x.x.x.jar test_script.txt

See [this section][rf-java-integration] for more details on using the standalone jar.

RobotFramework test execution can also be started directly from Java code, so it is easy to integrate this workflow with other Clojure tools if needed. See [this section][rf-java-api] of RobotFramework's documentation for details.

## Rationale

If Selenium already uses WebDriver, and if Watir-WebDriver already uses WebDriver, why another library? The fundamental rationales revolve around *language choice* and *API design*.

Clojure is a functional Lisp hosted on the JVM with a multitude of unique language features, such as data structures with built-in concurrency semantics, a powerful sequence abstraction API, and a satisfying Java interop story. These in addition to the "standard features" of a functional language such as immutable data structures, higher-order functions and excellent composability make Clojure a major contender in the world of programming languages. In the end, I wanted to write my functional web testing code in Clojure, not Ruby, Python or Java.

From an API perspective, [clj-webdriver][clj-webdriver-github] provides a relatively thin layer atop Selenium-WebDriver, and I personally like the Watir API in Ruby. However, due to the power of Clojure as a language, clj-webdriver's thin layer packs a big punch and provides levels of expression not possible in other languages. Moreover, because interoperating directly with Java libraries is trivial in Clojure, dropping down to the Java API for WebDriver is painless.

## Acknowledgements

Thanks to [Jari Bakken][jarib-github] for his [watir-webdriver][watir-webdriver-github] library, which has provided inspiration for various aspects of this library's API, as well as for his personal help over Github and the #watir IRC channel.

## License

Copyright (C) 2010 FIXME

Distributed under the Eclipse Public License, the same as Clojure.

[clj-webdriver-github]: https://github.com/semperos/clj-webdriver
[rrs-github]: https://github.com/semperos/robot-remote-server-clj
[jarib-github]: https://github.com/jarib
[watir-webdriver-github]: https://github.com/jarib/watir-webdriver
[rf-java-integration]: http://code.google.com/p/robotframework/wiki/JavaIntegration
[rf-java-api]: http://robotframework.googlecode.com/svn/tags/robotframework-2.5.6/doc/userguide/RobotFrameworkUserGuide.html#using-robot-framework-from-java
