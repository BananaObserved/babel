(ns sample-test-files.sample3
  (:require
   [corefns.corefns]))

(declare g2)
(defn g1 [x] (g2 x))
(defn g2 [x] (g1 x))

(defn eventuallyBadDivision [x y]
  (eventuallyBadDivision (/ x y) (- y 1)))
;; (eventuallyBadDivision 100 5)
;; (eventuallyBadDivision 100 100)

(defn divisionCurry [toDivideBy]
  (fn [x] (/ x toDivideBy)))
(def reversedDivisionList (map divisionCurry (range 100 -10 -1)))



