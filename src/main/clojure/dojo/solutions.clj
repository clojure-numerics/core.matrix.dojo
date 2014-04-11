(ns dojo.solutions
  (:use clojure.core.matrix)
  (:refer-clojure :exclude [+ - * / == ])
  (:require [clojure.core.matrix.operators :refer [+ - * / ==]])
  (:use clojure.repl)
  (:use dojo.data))

;; There is no right answer.....
;; But here are some suggested ideas / solutions for the challenge

;; ========================================================================
;; Challenge 1: computing stats for each team

(defn col [v col-name]
  "Utility function to extract a value from a result according to the clumn name"
  (mget v (COL-NUMS col-name)))

;; a map of column names -> column data
(def COLS (zipmap HEADINGS (for [h HEADINGS] (get-column RESULTS (COL-NUMS h)))))

;; matrix of home games for each team: 1 = home game, 0 = otherwise
(def HOME-GAMES (vec (for [team TEAMS] (mapv #(if (= team (col % "HomeTeam")) 1 0) (slices RESULTS)))))

;; matrix of away games for each team: 1 = home game, 0 = otherwise
(def AWAY-GAMES (vec (for [team TEAMS] (mapv #(if (= team (col % "AwayTeam")) 1 0) (slices RESULTS)))))

;; vectors of result types for each match 
(def HWINS (mapv #(if (= "H" (col % "FTR")) 1 0) (slices RESULTS)))
(def DRAWS (mapv #(if (= "D" (col % "FTR")) 1 0) (slices RESULTS)))
(def AWINS (mapv #(if (= "A" (col % "FTR")) 1 0) (slices RESULTS)))

;; check we have captured all games
(esum (add HWINS AWINS DRAWS))
;; => 380

;; add up results by team
(def HOME-WINS (mapv esum (slices (* HWINS HOME-GAMES))))
(def AWAY-WINS (mapv esum (slices (* AWINS AWAY-GAMES))))
(def HOME-LOSSES (mapv esum (slices (* AWINS HOME-GAMES))))
(def AWAY-LOSSES (mapv esum (slices (* HWINS AWAY-GAMES))))
(def DRAW-RESULTS (mapv esum (slices (add (* DRAWS AWAY-GAMES) (* DRAWS HOME-GAMES)))))

;; percentages
(def HOME-WIN-PERCENTAGE (/ HOME-WINS 19.0)) ;; note: division of vector by a scalar

;; wins, draws, losses for each team
(def TEAM-RESULTS
  (transpose [(+ HOME-WINS AWAY-WINS) DRAW-RESULTS (+ HOME-LOSSES AWAY-LOSSES)]))

;; end of season points for each team (3 = win, 1 = draw, 0 = loss)
(def TEAM-POINTS 
  (inner-product TEAM-RESULTS [3 1 0]))

;; create a sorted league table
(def LEAGUE-TABLE
  (sort-by #(- (second %)) (zipmap TEAMS TEAM-POINTS)))

;; Should match the Pts results at:
;;   http://en.wikipedia.org/wiki/2012%E2%80%9313_Premier_League

;; pretty-print the results
(comment 
  (pm LEAGUE-TABLE)
)
;; ========================================================================
;; Challenge 2: matrices of results

;; create a sorted map of TeamName -> team number (for array indexing)
(def TEAM-NUMS 
  (into (sorted-map) (zipmap TEAMS (range))))

;; 20x20 matrix of goals for home team
(def HOME-GOALS
  (reduce 
    (fn [m v] 
      (mset m 
            (TEAM-NUMS (col v "HomeTeam")) 
            (TEAM-NUMS (col v "AwayTeam"))
            (read-string (col v "FTHG")))) 
    (zero-matrix 20 20)
    (slices RESULTS)))

;; 20x20 matrix of goals for away team
(def AWAY-GOALS
  (reduce 
    (fn [m v] 
      (mset m 
            (TEAM-NUMS (col v "HomeTeam")) 
            (TEAM-NUMS (col v "AwayTeam"))
            (read-string (col v "FTAG")))) 
    (array (repeat 20 (repeat 20 "-")))
    (slices RESULTS)))

(defn match-result 
  "Helper function to calculate the result of a match, given home goals and away goals"
  ([hg ag]
      (cond 
        (or (string? hg) (string? ag)) "-"
        (> hg ag) "W"
        (< hg ag) "L"
        :else "D")))

;; use 'emap' compare these element-by-element to get 20x20 matrix of match results
(def MATCH-RESULTS
  (emap 
    match-result
    HOME-GOALS
    AWAY-GOALS))

;; print out the match results as a nice table
(comment 
  (pm MATCH-RESULTS)
)


;; ========================================================================
;; Challenge 3: time series

;; TODO
