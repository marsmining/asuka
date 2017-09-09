(defproject asuka "0.1.0-SNAPSHOT"
  :description "Pretend to be a UPnP device"
  :url "https://github.com/marsmining/asuka"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.4.0"]
                 [org.apache.logging.log4j/log4j-api "2.9.0"]
                 [org.apache.logging.log4j/log4j-core "2.9.0"]
                 [com.stuartsierra/component "0.3.2"]]
  :main ^:skip-aot asuka.core
  :profiles {:uberjar {:aot :all}
             :test    {:dependencies []}
             :dev     {:repl-options {:init-ns user}
                       :source-paths ["dev"]
                       :dependencies [[org.clojure/tools.namespace "0.2.11"]]}})
