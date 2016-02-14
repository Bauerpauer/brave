(ns websearch.core
  (:gen-class)
  (:require [websearch.search :as search]
            [clojure.tools.cli :refer [parse-opts]]))

(def cli-options [
                   ["-e" "--engines NAME" "Engine to use. Can be provided multiple times."
                    :default #{:bing :google :yahoo}
                    :default-desc "bing, google, yahoo"
                    :assoc-fn (fn [m k v]
                                (if-not (:custom-engines m)
                                  (merge m {:custom-engines true :engines #{(keyword v)}})
                                  (assoc-in m [k] (conj (k m) (keyword v)))))]
                   ["-t" "--timeout TIMEOUT" "Milliseconds to wait before quitting"
                    :default 5000
                    :parse-fn #(Integer/parseInt %)]])

(defn usage [options-summary]
 (->> ["Kinda-sorta a solution to the exercise for Chapter 9 of Clojure for the"
       "Brave and True. Executes concurrent searches on multiple search engines"
       "and prints the first result from the first search engine that responds."
       ""
       "Usage: lein run -- [options] <Search Term>"
       ""
       "Options:"
       options-summary]
      (clojure.string/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
    (clojure.string/join \newline errors)))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn -main
  [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)
        {:keys [engines]} options
        search-term (last arguments)]

    (cond
      (:help options) (exit 0 (usage summary))
      (nil? search-term) (exit 1 (usage summary))
      errors (exit 1 (error-msg errors)))

    (println "Searching for" (str "`" search-term "`") "in" (clojure.string/join ", " engines))

    (let [fastest-result (promise)
          search-options {:search-term search-term :result-promise fastest-result}
          futures (doall (map #(search/search-engines (merge {:key %1} search-options)) engines))
          fastest (deref fastest-result (:timeout options) nil)]

      (if (nil? fastest)
        (println (format "Sorry, nothing finished before the timeout (%d)ms" (:timeout options)))
        (println "Fastest Result" (get-in fastest [:first-result]) "from" (get-in fastest [:opts :url]))))))
