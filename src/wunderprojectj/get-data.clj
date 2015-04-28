(ns wunderprojectj.get-data
  (:require [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.data.zip.xml :as z-xml]))

;; We slurp the API key from a file so that the key can be kept private
(def api-key (slurp "./resources/private/wapikey.txt"))

;; Functions
(defn make-url
  "Given a  a country or a two-letter state followed by a city, returns a 
   Wunderground API URL"
  [c-s city]
  (str "http://api.wunderground.com/api/" 
       api-key "/conditions/q/" c-s "/" city ".xml"))

(defn get-zip
  "Given an API url, returns the parsed result as a zipper"
  [url]
  (zip/xml-zip (xml/parse url)))