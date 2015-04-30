(ns wunderprojectj.get-data
  (:require [clojure.xml :as xml]))

;; We slurp the API key from a file so that the key can be kept private
(def api-key (slurp "./resources/private/wapikey.txt"))

;; Functions
(defn make-url
  "Given a  a country or a two-letter state followed by a city, returns a 
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
    (reduce conj)
    ))