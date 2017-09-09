(ns user
  (:require [clojure.tools.logging :as log]
            [clojure.java.javadoc :refer [javadoc]]
            [clojure.pprint :refer [pprint]]
            [clojure.reflect :refer [reflect]]
            [clojure.test :refer [run-all-tests]]
            [clojure.repl :refer [apropos dir doc find-doc pst source]]
            [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [com.stuartsierra.component :as component]
            [asuka.core :as core]
            [asuka.util :as u])
  (:import [org.apache.logging.log4j LogManager]))

(defonce system nil)

(defn start
  [extra-config]
  (.reconfigure (LogManager/getContext false))
  (alter-var-root
   #'system
   (constantly (core/run (merge (u/load-dev-config) extra-config)))))

(defn stop []
  (alter-var-root #'system (fn [s] (when s (component/stop s)))))

(defn go
  ([extra-config]
   (start extra-config)
   :ready)
  ([] (go {})))

(defn reset []
  (stop)
  (refresh :after 'user/go))

(defn run-tests []
  (run-all-tests #"asuka.*-test"))

(defn tests []
  (stop)
  (refresh :after 'user/run-tests))

(comment

  (reset)

  )
