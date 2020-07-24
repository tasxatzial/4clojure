;p54: Partition a Sequence
;Write a function which returns a sequence of lists of x items each. Lists of less than x items should not be returned
;
;restrictions: partition, partition-all

;solution 1 ----------------------------------------
(def p54 (fn my-partition [N col]
           (take (Math/floor (/ (count col) N))  (concat (list (take N col)) (lazy-seq (my-partition N (drop N col)))))))

;solution 1 tests
(p54 3 (range 9))
(p54 2 (range 8))
(p54 3 (range 8))

;solution 2 ----------------------------------------
(def p54_2 (fn [N col]
             ((fn my-partition [result col]
                (if (< (count col) N)
                  result
                  (recur (concat result (list (take N col))) (drop N col))))
              '() col)))

;solution 2 tests
(p54_2 3 (range 9))
(p54_2 2 (range 8))
(p54_2 3 (range 8))

;solution 3 ----------------------------------------
(def p54_3 (fn [N col]
             (concat [] ((fn my-partition [col]             ;concat necessary if we pass empty seq
                           (when (>= (count col) N)
                             (cons (take N col) (my-partition (drop N col)))))
                         col))))

;solution 3 tests
(p54_3 3 (range 9))
(p54_3 2 (range 8))
(p54_3 3 (range 8))