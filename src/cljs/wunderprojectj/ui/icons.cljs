(ns wunderprojectj.ui.icons)

(def icons {"Chance of Flurries"	"chanceflurries"	
            "Chance of Rain"	"chancerain"	
            "Chance Rain"	"chancerain"	
            "Chance of Freezing Rain"	"chancesleet"	
            "Chance of Sleet"	"chancesleet"	
            "Chance of Snow"	"chancesnow"	
            "Chance of Thunderstorms"	"chancetstorms"	
            "Chance of a Thunderstorm"	"chancetstorms"	
            "Clear"	"clear"	
            "Cloudy"	"cloudy"	
            "Flurries"	"flurries"	
            "Fog"	"fog"	
            "Haze"	"hazy"	
            "Mostly Cloudy"	"mostlycloudy"	
            "Mostly Sunny"	"mostlysunny"	
            "Partly Cloudy"	"partlycloudy"	
            "Partly Sunny"	"partlysunny"	
            "Freezing Rain"	"sleet"	
            "Rain"	"rain"	
            "Sleet"	"sleet"	
            "Snow"	"snow"	
            "Sunny"	"sunny"	
            "Thunderstorms"	"tstorms"	
            "Thunderstorm"	"tstorms"	
            "Unknown"	"unknown"	
            "Overcast"	"cloudy"	
            "Scattered Clouds"	"partlycloudy"})

(defn icon-url
  "Generates a URL for a Wunderground icon given the condition string"
  [conds]
  (if-let [icon (get icons conds)]
    (str "http://icons.wxug.com/i/c/i/" icon ".gif")
    (icon-url "Unknown")))