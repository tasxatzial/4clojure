;p39: Interleave Two Seqs
;Write a function which takes two sequences and returns the first item from each, then the second item from each,
;then the third, etc
;
;restrictions: interleave
;
(def p39 (fn [col1 col2]
           (reduce concat (map (fn [x1 x2]
                                 [x1 x2]) col1 col2))))

;tests
(p39 [1 2 3] [:a :b :c])
(p39 [1 2] [3 4 5 6])
(p39 [1 2 3 4] [5])
(p39 [30 20] [25 15])