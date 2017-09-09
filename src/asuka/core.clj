(ns asuka.core
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [asuka.util :refer [get-jvm-args get-jvm-collector-info]]
            [asuka.discovery :refer [map->Discovery]])
  (:gen-class))

(def main-config {})

(defn run
  "Initializes and starts the whole system."
  [override-config]
  (log/info "Asuka booting..")
  (log/info "JVM Args:" (get-jvm-args))
  (log/info "JVM Collector Info:" (get-jvm-collector-info))
  (log/info "Override config:" override-config)
  (let [config (merge main-config override-config)
        system
        (component/map->SystemMap
          {:discovery (map->Discovery
                        {:config config})})]
    (component/start system)))

(defn -main
  "The actual main for our uberjar."
  [& args]
  (try
    (run {})
    (catch Exception e
      (log/error e "Could not start system because of %s." (str e))
      (System/exit 1))))
