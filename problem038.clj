;p38: Maximum value
;Write a function which takes a variable number of parameters and returns the maximum value
;
;restrictions: max, max-key

;solution 1 ----------------------------------------
(def p38 (fn [& col]
           (if (empty? col)
             nil
             ((fn my-max [x col]
                (if (empty? col)
                  x
                  (if (> x (first col))
                    (recur x (rest col))
                    (recur (first col) (rest col)))))
              (first col) (rest col)))))

;solution 1 tests
(map #(apply p38 %) [[1 8 3 4] [30 20] [45 67 11]])

;solution2 ----------------------------------------
(def p38_2 (fn [& col]
             (reduce (fn [max_ x]
                       (if (> x max_)
                         x
                         max_))
                     (first col) (rest col))))

;solution 2 tests
(map #(apply p38_2 %) [[1 8 3 4] [30 20] [45 67 11]])