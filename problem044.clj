;p44: Rotate Sequence
;Write a function which can rotate a sequence in either direction
(def p44 (fn [N col]
           (let [N (mod N (count col))]
             (concat (drop N col) (take N col)))))

;tests
(p44 6 [1 2 3 4 5])
(p44 -2 [1 2 3 4 5])
(p44 6 [1 2 3 4 5])
(p44 1 '(:a :b :c))
(p44 -4 '(:a :b :c))