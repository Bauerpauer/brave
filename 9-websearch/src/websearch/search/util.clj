(ns websearch.search.util
  (:require [org.httpkit.client :as http])
  (:use net.cgrand.enlive-html)
  (:import java.net.URL))

(defn get-search-result
  "Uses http-kit to asynchronously grab the `url`. On success, parses the result
   and delivers it to the promise"
  [url result-parser the-promise]
  (let [handler (fn [{body :body :as response}]
                  (deliver the-promise (assoc response :first-result (result-parser body))))]
    (http/get url {:user-agent "Mozilla"} handler)))
