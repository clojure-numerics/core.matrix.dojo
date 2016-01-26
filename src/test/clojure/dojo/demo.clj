(ns dojo.demo
  (:use [clojure.core.matrix])
  (:use [clojure.core.matrix.stats :as stats])
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
  
  ;; average number of home goals (at full time)
  (stats/mean (ds/column data "FTHG"))

  ;; average number of away goals (at full time)
  (stats/mean (ds/column data "FTAG"))

  (def hg-avg 
    (into {}
      (map 
        (fn [[team results]] [team (stats/mean (mapv second results))])
        (group-by first (mapv vector (ds/column data "HomeTeam") (emap read-string (ds/column data "FTHG")))))))
)


;; Incanter stuff

(comment
  ;; histogram
  (ic/view (ich/histogram (is/sample-normal 1000)))
  
  ;; function plot
  (ic/view (ich/function-plot logistic -10 10))
  
  ;; bar chart
  (ic/view (ich/bar-chart (keys hg-avg) (vals hg-avg)))
  
  ;; sorted bars
  (let [hg-avg (reverse (sort-by second hg-avg))]
    (ic/view (ich/bar-chart (map first hg-avg) (map second hg-avg))))
)