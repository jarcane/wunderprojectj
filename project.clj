(defproject wunderprojectj "0.1.0-SNAPSHOT"
  :description "A simple web app providing weather from three cities"
  :url "https://github.com/jarcane/wunderprojectj"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [org.clojure/data.zip "0.1.1"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler wunderprojectj.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
