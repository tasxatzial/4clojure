;p43: Reverse Interleave
;Write a function which reverses the interleave process into x number of subsequences

;solution 1 ----------------------------------------
(def p43 (fn [col N]
           (let [T (/ (count col) N)]
             ((fn my-rinterleave [result col]
                (if (empty? col)
                  result
                  (recur (concat result (list (take T col))) (nthrest col T))))
              '() (apply interleave (partition N col))))
           ))

;solution 1 tests
(p43 [1 2 3 4 5 6] 2)
(p43 (range 9) 3)
(p43 (range 10) 5)

;solution 2 ----------------------------------------
(def p43_2 (fn [col N]
             (let [col_new (partition N col) count_col (count (first col_new))]
               ((fn my-rinterleave [result C]
                  (if (= count_col C)
                    result
                    (recur (concat result (list (map #(nth % C) col_new)) ) (+ C 1))))
                '() 0))))

;solution 2 tests
(p43_2 [1 2 3 4 5 6] 2)
(p43_2 (range 9) 3)
(p43_2 (range 10) 5)

;solution 3 ----------------------------------------
(def p43_3 (fn [col N]
             (let [partitioned (partition N col) interleaved (apply interleave partitioned)]
               (partition (count partitioned) interleaved))))

;solution 3 tests
(p43_3 [1 2 3 4 5 6] 2)
(p43_3 (range 9) 3)
(p43_3 (range 10) 5)

;solution 4 ----------------------------------------
(def p43_4 (fn [col N]
             (apply map list (partition N col))))

;solution 4 tests
(p43_4 [1 2 3 4 5 6] 2)
(p43_4 (range 9) 3)
(p43_4 (range 10) 5)