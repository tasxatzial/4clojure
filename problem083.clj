;p83: A Half-Truth
;Write a function which takes a variable number of booleans. Your function should return true if some of the
;parameters are true, but not all of the parameters are true. Otherwise your function should return false
(def p83 (fn [& args]
           (and (or (some true? args) false)  (or (some false? args) false))))

;tests
(p83 false false)
(p83 true false)
(p83 true)
(p83 false true false)
(p83 true true true)
(p83 true true true false)