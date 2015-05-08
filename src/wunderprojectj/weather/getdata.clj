(ns wunderprojectj.weather.getdata
  (:require [clojure.xml :as xml]
            [clojure.data.xml :as d-xml]
            [ororo.core :as ororo]))

;; We slurp the API key from a file so that the key can be kept private
(def api-key (slurp "./resources/private/wapikey.txt"))

;; Functions
(defn find-conds
  "Given a list of observation maps, returns an estimation of weather conditions 
   for that day by grabbing either mid-day or the last available"
  [omap]
  (if-let [weather (->>
                     omap
                     (filter #(= (:hour (:date %)) "12"))
                     first
                     :conds)]
    weather
    (->> omap last :conds)))

(defn get-simple
  "Returns a simplified map containing just the temp and weather"
  [loc wmap]
  (let [loc (clojure.string/join ", " loc)
        temp (->> wmap :dailysummary first :meantempm)
        conds (find-conds (:observations wmap))] 
    {:location loc
     :temp temp 
     :weather conds}))

(defn simple->xml
  "Takes the simplified map of get-simple and returns an assembled XML response"
  [smap]
  (d-xml/sexp-as-element
    [:response
     [:location (:location smap)]
     [:temp (:temp smap)]
     [:weather (:weather smap)]]))

(defn weather-query
  "Given a location and date, returns XML response with location,
   current temp in C, and weather"
  [loc date]
  (->> 
    (ororo/history api-key loc date)
    (get-simple loc)
    simple->xml
    d-xml/emit-str))