;p23: Reverse a Sequence
;Write a function which reverses a sequence
;
;restrictions: reverse, rseq
;
(def p23 (fn my-reverse [col]
           (reduce (fn [result x]
                     (conj result x))
                   '() col)))

;tests
(map p23 [[1 2 3 4 5] (sorted-set 5 7 2 7) [[1 2][3 4][5 6]]])