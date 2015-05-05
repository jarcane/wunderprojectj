(ns wunderprojectj.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [wunderprojectj.weather.checkdb :as weather]))

(defroutes app-routes
  (GET "/london" [] (fn [req] (weather/query-city ["London" "England"])))
  (GET "/durham" [] (fn [req] (weather/query-city ["Durham" "NC"])))
  (GET "/tampere" [] (fn [req] (weather/query-city ["Tampere" "Finland"])))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
