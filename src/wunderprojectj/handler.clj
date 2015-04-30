(ns wunderprojectj.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [wunderprojectj.weather.getdata :as weather]))

(defroutes app-routes
  (GET "/london" [] (fn [req] (weather/weather-query "London" "England")))
  (GET "/durham" [] (fn [req] (weather/weather-query "Durham" "NC")))
  (GET "/tampere" [] (fn [req] (weather/weather-query "Tampere" "Finland")))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
