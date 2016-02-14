(ns websearch.search
  (:require [websearch.search.bing :as bing]
            [websearch.search.yahoo :as yahoo]
            [websearch.search.google :as google]))

(defmulti search-engines :key)
(defmethod search-engines :default [{:keys [key search-term result-promise]}]
  (println (format "Unknown Search Engine `%s`, ignoring." key)))

(defmethod search-engines :bing [{:keys [search-term result-promise]}]
  (bing/search search-term result-promise))

(defmethod search-engines :google [{:keys [search-term result-promise]}]
  (google/search search-term result-promise))

(defmethod search-engines :yahoo [{:keys [search-term result-promise]}]
  (yahoo/search search-term result-promise))
