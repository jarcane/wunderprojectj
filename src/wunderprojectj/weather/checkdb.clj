(ns wunderprojectj.weather.checkdb
  (:require [monger.core :as mg]
            [clj-time.core :as time]
            [wunderprojectj.weather.getdata :as weather]))

(defn date-exists
  "Checks the local weather database for a result from the current date
   Returns false if not found, or returns the stored result"
  [date city]
  false)

(defn cache-new
  "Makes an API request for the name and date, stores it, and returns it"
  [date city]
  (let [result (apply weather/weather-query city)]
    result))

(defn query-city
  "Queries the database for the current date. If found, returns stored value.
   If not found, queries the wunderground API, caches the result, and returns it"
  [city]
  (let [date (str (time/today))
        conn (mg/connect)]
    (if-let [exists (date-exists date city)]
      exists
      (cache-new date city))))