(ns dojo.demo
  (:use [clojure.core.matrix])
  (:use [clojure.repl])
  (:require [clojure.data.csv :as csv])
  (:require [clojure.java.io :as io])
  (:require [clojure.core.matrix.dataset :as ds])
  (:require [incanter.core :as ic]
            [incanter.charts :as ich]
            [incanter.stats :as is]))

;; Dataset loading

(comment
  (def data 
    (with-open [in-file (io/reader (io/resource "EPL-2012-13.csv"))]
      (let [lines (doall (csv/read-csv in-file))
            names (first lines)
            rows (next lines)]
        (ds/dataset names rows)))) 

  (shape data)
  
  (ds/column-names data)
  
  (distinct (ds/column data "HomeTeam"))
)

;; Incanter stuff

(comment
  ;; histogram
  (ic/view (ich/histogram (is/sample-normal 1000)))
  
  ;; function plot
  (ic/view (ich/function-plot logistic -10 10))
)