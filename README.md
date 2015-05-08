# wunderprojectj

A simple API server providing weather from three cities, provided by Wunderground

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed. You will also need [MongoDB][] 2.0+ installed.

[leiningen]: https://github.com/technomancy/leiningen
[mongodb]: https://www.mongodb.org/

## Setup

You will need to create a text file containing a valid Wunderground API key in the resources/private directory called wapikey.txt. (this is basically so I can work on this in a public repo without blasting my poor API key all over). 

I also recommend creating a local directory for the database off the root directory. I use `./data/db`

## Running

To start, run mongod, pointed to the database directory, then start up the server with lein:

	mongod --dbpath ./data/db

    lein ring server

## Usage

Once the server is running, the current weather in selected locations can be queried via HTTP GET on port 3000, from the appropriate url, composed of the name of the city (one of three) and the date in YYYYMMDD format:

* /london/YYYYMMDD -> London, England
* /tampere/YYYYMMDD -> Tampere, Finland
* /durham/YYYYMMDD -> Durham, NC, USA

The server will serve up the current temp and weather in these locations as an XML response, containing the location name, temp (in Celsius), and weather condition. 

Example mock output from `localhost:3000\london\20100515`: 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<response>
	<location>London, England</location>
	<temp>11</temp>
	<weather>Cloudy</weather>
</response>
```

## License

Copyright © 2015 John Berry
