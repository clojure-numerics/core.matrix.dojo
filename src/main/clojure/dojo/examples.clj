(ns dojo.examples
  (:use clojure.core.matrix))

;; construct a 1D array (i.e. a vector)
(array [1 2]) 

;; construct a 2D array (i.e. a matrix)
(array [[1 2] [3 4]]) 

;; add two arrays
(add [1 2] [3 4]) 

;; add with broadcasting
(add [1 2 3] 10) 

;; matrix transposition
(transpose [[1 2] [3 4]])



;; Use a specific matrix implementation: in this case vectorz-clj
(set-current-implementation :vectorz)

