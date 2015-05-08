(ns wunderprojectj.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [wunderprojectj.weather.checkdb :as weather]))

(defroutes app-routes
  (GET "/london/:date" [date] (fn [req] (weather/query-city ["London" "England"] date)))
  (GET "/durham/:date" [date] (fn [req] (weather/query-city ["Durham" "NC"] date)))
  (GET "/tampere/:date" [date] (fn [req] (weather/query-city ["Tampere" "Finland"] date)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
