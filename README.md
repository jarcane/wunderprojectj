# wunderprojectj

A simple web API providing access to current weather data from a select list of cities provided by Wunderground.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Setup

You will need to create a text file containing a valid Wunderground API key in the resources/private directory called wapikey.txt. (this is basically so I can work on this in a public repo without blasting my poor API key all over).

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2015 John Berry
