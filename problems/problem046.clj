;p46: Flipping out
;Write a higher-order function which flips the order of the arguments of an input function
(def p46 (fn [func]
           (fn [arg1 arg2]
             (func arg2 arg1))))

;tests
((p46 nth) 2 [1 2 3 4 5])
((p46 >) 7 8)
((p46 quot) 2 8)
((p46 take) [1 2 3 4 5] 3)