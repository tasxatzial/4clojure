;p34: Implement range
;Write a function which creates a list of all integers in a given range
;
;restrictions: range
;
(def p34 (fn my-range [N1 N2]
           (when (< N1 N2)
             (cons N1 (my-range (+ N1 1) N2))))) ;(cons X nil) -> (X)

;tests
(p34 1 4)
(p34 -2 2)
(p34 5 8)