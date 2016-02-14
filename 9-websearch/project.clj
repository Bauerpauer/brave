(defproject websearch "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [http-kit "2.1.18"]
                 [enlive "1.1.5"]
                 [com.cemerick/url "0.1.1"]]
  :main ^:skip-aot websearch.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
