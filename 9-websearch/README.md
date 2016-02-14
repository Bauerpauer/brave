# websearch

Exercise for Chapter 9 of Clojure for the Brave and True (http://www.braveclojure.com/concurrency/)

## Usage

    $ lein run -- -e yahoo -e google -e bing -t1500 "german village apartments"

## Options

    -e, --engines NAME     bing, google, yahoo  Engine to use. Can be provided multiple times.
    -t, --timeout TIMEOUT  5000                 Milliseconds to wait before quitting
