(ns websearch.search.google
  (:require [websearch.search.util :as util]
            [cemerick.url :refer [url-encode]])
  (:use net.cgrand.enlive-html)
  (:import java.net.URL))

(defn find-first-result [html-string]
  (-> (html-resource (java.io.StringReader. html-string))
      (select [:h3.r :a]) first :attrs :href))

(defn search-url [search-term]
  (format "https://www.google.com/search?q=%s" (url-encode search-term)))

(defn search [search-term result-promise]
  (util/get-search-result (search-url search-term) find-first-result result-promise))
