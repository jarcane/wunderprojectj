(ns wunderprojectj.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [wunderprojectj.weather.checkdb :as weather]))

(defroutes app-routes
  (GET "/:city/:cs/:date" [city cs date] (fn [req] (weather/query-city [city cs] date)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
