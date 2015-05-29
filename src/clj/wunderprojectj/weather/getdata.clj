(ns wunderprojectj.weather.getdata
  (:require [clojure.data.xml :as d-xml]
            [ororo.core :as ororo]
            [cheshire.core :refer :all]
            [clojure.string :as string]))

;; We slurp the API key from a file so that the key can be kept private
(def api-key (string/trim (slurp "./resources/private/wapikey.txt")))

;; Functions
(defn find-conds
  "Given a list of observation maps, returns an estimation of weather conditions 
   for that day by grabbing either mid-day or the last available"
  [omap]
  (if-let [weather (->> omap
                        (filter #(= (:hour (:date %)) "12"))
                        first
                        :conds)]
    weather
    (->> omap last :conds)))

(defn get-simple
  "Returns a simplified map containing just the location, temp and weather"
  [loc wmap]
  (let [loc (string/join ", " (map string/capitalize loc))
        temp (->> wmap 
                  :dailysummary 
                  first 
                  :meantempm)
        conds (find-conds (:observations wmap))] 
    {:location loc
     :temp temp 
     :weather conds}))

(defn weather-query
  "Given a location and date, returns JSON response with location,
   current temp in C, and weather"
  [loc date]
  (->> (ororo/history api-key loc date)
       (get-simple loc)
       generate-string))
