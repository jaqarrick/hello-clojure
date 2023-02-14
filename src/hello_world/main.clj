(ns hello-world.main
  (:require [hello-world.cars :as cars] [org.httpkit.server :as http]))

(+ 1 2)

(defn hello [] "hello world")
(cars/cars)

(defmulti move (fn [x] (:mechanism x)))
(defmethod move :motor [_] "vroom")
(defmethod move :feet [_] "walk")

(move {:mechanism :motor})
(move {:mechanism :feet})



(defonce stop-server (atom nil)) ; value on init

(reset! stop-server "heyyy")
(deref stop-server)

(defn stop! []
  (when-not (nil? @stop-server)
    (println "stopping server")
    (@stop-server :timeout 100)
    (reset! stop-server nil)
    ))
(defn log-ip [req] (println (:remote-addr req)))

(comment
  (stop!))

(defn app [req]
  (log-ip req)
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str "hello client number: " (rand-int 100) "\n")})

(reset! stop-server (http/run-server #'app {:port 8081}))







