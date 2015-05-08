(ns wunderprojectj.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [wunderprojectj.weather.checkdb :as weather]))

(def cities {:london ["London" "England"]
             :durham ["Durham" "NC"]
             :tampere ["Tampere" "Finland"]})

(defn req-parse
  "Parses the request parameters, passes them to the weather query function, 
   and returns an XML response as a string"
  [city date]
  (let [cityv ((keyword city) cities)]
    (weather/query-city cityv date)))

(defroutes app-routes
  (GET "/:city/:date" [city date] (fn [req] (req-parse city date)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
