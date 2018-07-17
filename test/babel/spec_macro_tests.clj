(ns babel.spec-macro-tests
  (:require
   [babel.error-tests :refer :all]
   [expectations :refer :all]))

;;you need to have launched a nREPL server in babel for these to work.
;;this must be the same port specified in project.clj

;############################################
;########## Testing for 'let' ###############
;############################################

(expect "Parameters for let must come in pairs, but one of them does not have a match.\n"
        (get-error "(defn hello [x] (let [y 2 z] (+ x y)))"))

(expect "Parameters for let must come in pairs, but one of them does not have a match.\n"
        (get-error "(defn hello [x] (let [y] (+ x y)))"))

;; I am not sure this is what we want, but this is how it currently works -Elena
(expect "Parameters for let must come in pairs, but one of them does not have a match.\n"
        (get-error " (let [[a b]] (+ a b))"))

(expect "Parameters for let require a vector, instead, 'a' was given.\n"
        (get-error " (let a (+ a 2))"))

;############################################
;#### Testing for 'let-like forms ###########
;############################################

(expect "Parameters for if-let must be a pair, but only one element is given.\n"
        (get-error "(if-let [x] x)"))

(expect "Parameters for if-let must be only one name and one value, but more parameters were given.\n"
        (get-error "(if-let [x 2 y] x)"))

(expect "In let 2 is used instead of a variable name.\n"
        (get-error "(let [2 3] 8)"))

;############################################
;#### Testing for 'defn' ###########
;############################################

(expect "In defn [b c] is used instead of a function name.\n"
        (get-error "(defn [b c] (+ 4 3))"))

(expect "An argument for defn required a vector, instead, 'x' was given.\n"
        (get-error "(defn afunc2 x (+ 3 x))"))

;############################################
;#### Testing for 'fn' ###########
;############################################
(expect "An argument for fn required a vector, instead, 'VARIABLE-NAME' was given.\n"
        (get-error "(map (fn fn-name1 VARIABLE-NAME (* 4 VARIABLE-NAME)) (range 1 10))"))

(expect "An argument for fn required a vector, instead, 'VARIABLE-NAME' was given.\n"
        (get-error "(map (fn VARIABLE-NAME (* 4 VARIABLE-NAME)) (range 1 10))"))

(expect "An argument for fn required a vector, but no vector was passed.\n"
        (get-error "(map (fn (* 4 VARIABLE-NAME)) (range 1 10))"))
