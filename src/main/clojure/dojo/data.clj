(ns dojo.data
  (:use [clojure.core.matrix])
  (:require [clojure.data.csv :as csv])
  (:require [clojure.java.io :as io]))

;; load all data from the CVS file in src/main/resources
(def DATA-LINES 
  (with-open [in-file (io/reader (io/resource "EPL-2012-13.csv"))]
    (doall (csv/read-csv in-file))))

;; The headings from the CVS file
(def HEADINGS (first DATA-LINES))

;; The table of results
(def RESULTS (vec (rest DATA-LINES)))

;; Map of column names to column numbers
(def COL-NUMS (zipmap HEADINGS (range)))

;; Collect the list  of all teams (sorted alphabetically)
(def TEAMS 
  (-> RESULTS
    (get-column (COL-NUMS "HomeTeam"))
    distinct
    sort
    vec))

;; check we have the right number of total teams - there should be 20!
(count TEAMS)