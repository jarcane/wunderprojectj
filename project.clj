(defproject wunderprojectj "0.1.0-SNAPSHOT"
  :description "A simple web app providing weather from three cities"
  :url "https://github.com/jarcane/wunderprojectj"

  :source-paths ["src/clj" "src/cljs"]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring-server "0.4.0"]
                 [cljsjs/react "0.13.1-0"]
                 [reagent "0.5.0"]
                 [reagent-forms "0.5.0"]
                 [reagent-utils "0.1.4"]
                 [ring "1.3.2"]
                 [ring/ring-defaults "0.1.4"]
                 [prone "0.8.1"]
                 [compojure "1.3.3"]
                 [hiccup "1.0.5"]
                 [environ "1.0.0"]
                 [org.clojure/clojurescript "0.0-3211" :scope "provided"]
                 [secretary "1.2.3"]
                 [org.clojure/data.xml "0.0.8"]
                 [com.novemberain/monger "2.0.1"]
                 [ororo "0.1.0"]
                 [cheshire "5.4.0"]
                 [cljs-http "0.1.30"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]]

  :plugins [[lein-ring "0.9.1"]
            [lein-environ "1.0.0"]
            [lein-asset-minifier "0.2.2"]]

  :ring {:handler wunderprojectj.handler/app
         :uberwar-name "wunderprojectj.war"}

  :min-lein-version "2.5.0"

  :uberjar-name "wunderprojectj.jar"

  :main wunderprojectj.server

  :clean-targets ^{:protect false} ["resources/public/js"]

  :minify-assets
  {:assets
    {"resources/public/css/site.min.css" "resources/public/css/site.css"}}

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to     "resources/public/js/app.js"
                                        :output-dir    "resources/public/js/out"
                                        :asset-path   "js/out"
                                        :figwheel true
                                        :optimizations :none
                                        :pretty-print  true}}}}

  :profiles {:dev {:repl-options {:init-ns wunderprojectj.repl
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :dependencies [[ring-mock "0.1.5"]
                                  [ring/ring-devel "1.3.2"]
                                  [weasel "0.6.0"]
                                  ;[leiningen-core "2.5.1"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.10"]
                                  [pjstadig/humane-test-output "0.7.0"]
                                  ;[figwheel "0.3.1"]
                                  ]

                   :source-paths ["env/dev/clj"]
                   :plugins [[lein-figwheel "0.3.1"]
                             [lein-cljsbuild "1.0.5"]]

                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]

                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :repl false
                              :css-dirs ["resources/public/css"]
                              :ring-handler wunderprojectj.handler/app}

                   :env {:dev true}

                   :cljsbuild {:builds {:app {:source-paths ["env/dev/cljs"]
                                              :compiler {:main "wunderprojectj.dev"
                                                         :source-map true}}
}
}}

             :uberjar {:hooks [leiningen.cljsbuild minify-assets.plugin/hooks]
                       :env {:production true}
                       :aot :all
                       :omit-source true
                       :cljsbuild {:jar true
                                   :builds {:app
                                             {:source-paths ["env/prod/cljs"]
                                              :compiler
                                              {:optimizations :advanced
                                               :pretty-print false}}}}}})
