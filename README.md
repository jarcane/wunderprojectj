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

To start, run mongod, pointed to the database directory, then start up the server with lein:

	mongod --dbpath ./data/db

	lein cljsbuild auto

    lein ring server

## Usage

Once the server is running, the front-end will be accessible from localhost at port 3000. In dev mode, a figwheel connection is available on port 3449, and a weasel REPL is available on port 9001.

The current weather in selected locations can be queried via HTTP GET on port 3000, with the appropriate URL parameters. URLs are constructed like so:

	/[city]/[country or two-letter state code]/[date in YYYYMMDD]

All parameters are required or a "Not Found" result will be returned.

The server will serve up the current temp and weather in these locations as an XML response, containing the location name, temp (in Celsius), and weather condition. 

Example input:
	localhost:3000/london/england/20100515

Example output: 
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
