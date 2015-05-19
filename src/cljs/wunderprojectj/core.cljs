(ns wunderprojectj.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [cljsjs.react :as react]
              [clojure.string :as string]
              [cljs-http.client :as http]
              [cljs.core.async :refer [<!]])
    (:require-macros [cljs.core.async.macros :refer [go]])
    (:import goog.History))

(defonce locmap {"london/england" "London, England"
                 "tampere/finland" "Tampere, Finland"
                 "durham/nc" "Durham, NC, USA"})

(defonce location (atom "london/england"))
(defonce date (atom ""))
(defonce curr-weather (atom ""))

(defn url-create 
  "Create the URL needed to call the wunderproject back-end from the location
   and date atoms"
  [loc date]
  (str "http://localhost:3449/" loc "/" (string/replace date #"-" "")))

(defn load-data
  "Retrieve a JSON response from the back end"
  [loc date]
  (go (let [response (<! (http/get (url-create loc date)))]
        (reset! curr-weather (:body response)))))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to wunderprojectj"]
   [:div [:select 
          {:value @location 
           :on-change #(reset! location (-> % .-target .-value))}
          (for [item locmap]
            ^{:key (key item)} [:option {:value (key item)} (val item)])]]
   [:div [:input {:type "date"
                  :value @date
                  :on-change #(reset! date (-> % .-target .-value))}]]
   [:div 
    [:button {:type "button"
              :on-click #(load-data @location @date)}
     "Get Weather"]]   
   [:div (let [curr @curr-weather
               {loc :location
                temp :temp
                conds :weather} curr]
           [:div 
            [:p "Location: " loc]
            [:p "Temperature: " temp]
            [:p "Conditions: " conds]])]])

;; -------------------------
;; Initialize app
(defn mount-root []
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
