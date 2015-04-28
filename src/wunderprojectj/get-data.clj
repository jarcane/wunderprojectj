(ns wunderprojectj.get-data
  (:use [clojure.data.zip.xml])
  (:require [clojure.xml :as xml]
            [clojure.zip :as zip]))

(def api-key (slurp "./resources/private/wapikey.txt"))

