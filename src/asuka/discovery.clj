(ns asuka.discovery
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]))

(defrecord Discovery [config]
  component/Lifecycle
  (start [component]
    (log/info "Starting Discovery component..")
    component)
  (stop [component]
    (log/info "Stopping Discovery component..")
    component))
