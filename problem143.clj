;p143: dot product
;Create a function that computes the dot product of two sequences. You may assume that the vectors will have the
;same length
(def p143 (fn [col1 col2]
            (reduce + (map * col1 col2))))

;tests
(p143 [0 1 0] [1 0 0])
(p143 [1 1 1] [1 1 1])
(p143 [1 2 3] [4 5 6])
(p143 [2 5 6] [100 10 1])