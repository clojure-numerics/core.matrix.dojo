(ns dojo.solutions
  (:use clojure.core.matrix)
  (:use clojure.core.matrix.operators)
  (:use clojure.repl)
  (:use dojo.data))

;; There is no right answer.....
;; But here are some suggested ideas / solutions for the challenge

;; a map of column names -> column data
(def COLS (zipmap HEADINGS (for [h HEADINGS] (get-column RESULTS (COL-NUMS h)))))

;; matrix of home games for each team: 1 = home game, 0 = otherwise
(def HOME-GAMES (vec (for [team TEAMS] (mapv #(if (= team (mget % (COL-NUMS "HomeTeam"))) 1 0) (slices RESULTS)))))

;; matrix of away games for each team: 1 = home game, 0 = otherwise
(def AWAY-GAMES (vec (for [team TEAMS] (mapv #(if (= team (mget % (COL-NUMS "AwayTeam"))) 1 0) (slices RESULTS)))))

;; vectors of result types for each match
(def HWINS (mapv #(if (= "H" (mget % (COL-NUMS "FTR"))) 1 0) (slices RESULTS)))
(def DRAWS (mapv #(if (= "D" (mget % (COL-NUMS "FTR"))) 1 0) (slices RESULTS)))
(def AWINS (mapv #(if (= "A" (mget % (COL-NUMS "FTR"))) 1 0) (slices RESULTS)))

;; check we have captured all games
(esum (add HWINS AWINS DRAWS))
;; => 380

;; add up results by team
(def HOME-WINS (mapv esum (slices (* HWINS HOME-GAMES))))
(def AWAY-WINS (mapv esum (slices (* AWINS AWAY-GAMES))))
(def DRAW-RESULTS (mapv esum (slices (add (* DRAWS AWAY-GAMES) (* DRAWS HOME-GAMES)))))

;; percentages
(def HOME-WIN-PERCENTAGE (/ HOME-WINS 19.0)) ;; note: division of vector by a scalar
