(ns wunderprojectj.get-data
  (:require [clojure.xml :as xml]))

;; We slurp the API key from a file so that the key can be kept private
(def api-key (slurp "./resources/private/wapikey.txt"))

;; Functions
(defn make-url
  "Given a country or a two-letter state followed by a city, returns a 
   Wunderground API URL"
  [c-s city]
  (str "http://api.wunderground.com/api/" 
       api-key "/conditions/q/" c-s "/" city ".xml"))

(defn get-conditions
  "Returns a map of the current weather conditions"
  [url]
  (->> 
    (xml/parse url)
    :content
    (filter #(= (:tag %) :current_observation))
    first
    :content
    (mapv #(let [nk (:tag %)
                 nv (:content %)]
             (hash-map nk nv)))
    (reduce conj)))

(defn get-simple
  "Returns a simplified map containing just the temp and weather"
  [wmap]
  (select-keys wmap [:temp_c :weather]))

(defn weather-query
  "Given a city and country/state, returns map of current temp in C and weather"
  [city cs]
  (->> 
    (make-url cs city)
    get-conditions
    get-simple
    ))