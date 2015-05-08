(ns wunderprojectj.weather.checkdb
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [clj-time.core :as time]
            [wunderprojectj.weather.getdata :as weather]))

(def conn (mg/connect))
(def db (mg/get-db conn "wunderprojectj"))

(defn date-exists
  "Checks the local weather database for a result from the current date
   Returns false if not found, or returns the stored result"
  [coll date]
  (let [result (mc/find-one-as-map db coll {:date date})]
    (if (empty? result)
      false
      (:response result))))

(defn cache-new
  "Makes an API request for the name and date, stores it to the local DB, 
   and returns it"
  [coll date city]
  (let [result (weather/weather-query city date)]
    (mc/insert db coll {:date date :response result})
    result))

(defn query-city
  "Takes a city (a vector containing city at index 1 and country/state at index 2)
   queries the DB for a cached result from the current day and returns it if found
   or requests a new result if not found"
  [city]
  (let [date (clojure.string/join (remove #(= % \-) (str (time/today))))
        coll (first city)]
    (if-let [exists (date-exists coll date)]
      exists
      (cache-new coll date city))))