# wunderprojectj

A simple web application providing weather from three cities.

Data is provided by Wunderground.

## Prerequisites

You will need [Leiningen][] 2.5.0 or above installed. You will also need [MongoDB][] 2.0+ installed.

[leiningen]: https://github.com/technomancy/leiningen
[mongodb]: https://www.mongodb.org/

## Setup

You will need to create a text file containing a valid Wunderground API key in the resources/private directory called wapikey.txt. (this is basically so I can work on this in a public repo without blasting my poor API key all over). 

I also recommend creating a local directory for the database off the root directory. I use `./data/db`

## Running

To start, run mongod, pointed to the database directory, then start up the server in dev mode with lein:

	mongod --dbpath ./data/db

	lein figwheel

## Usage

In dev-mode, once the server is running, the front-end will be accessible from localhost at port 3449, with a simple front-end available at the root offering access to data from three major cities.

The current weather in selected locations can be queried via HTTP GET, with the appropriate URL parameters. URLs are constructed like so:

	/[city]/[country or two-letter state code]/[date in YYYYMMDD]

All parameters are required or a "Not Found" result will be returned.

The server will serve up the temp and weather in these locations as a JSON response, containing the location name, temp (in Celsius), and weather condition. 

Example:
```json
{ "location" : "London, England",
  "temp" : "0",
  "weather" : "Partly Cloudy" }
```

## License

Copyright © 2015 John Berry

Built using [reagent-template](https://github.com/reagent-project/reagent-template)
