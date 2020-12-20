;p112: Sequs Horribilis
;Create a function which takes an integer and a nested collection of integers as arguments. Analyze the elements
;of the input collection and return a sequence which maintains the nested structure, and which includes all
;elements starting from the head whose sum is less than or equal to the input integer
(def p112 (fn [N col]
             ((fn my-sh [col sm fcol]
                (if (empty? col)
                  fcol
                  (if (sequential? (first col))
                    (let [f (memoize (fn [col]
                                       (my-sh (first col) sm '())))]
                      (recur (next col) (+ sm (reduce + (flatten (f col)))) (concat fcol [(f col)])))
                    (if (< N (+ sm (first col)))
                      fcol
                      (recur (next col) (+ sm (first col)) (concat fcol [(first col)]))))))
              col 0 '())))

;tests
(p112 10 [1 2 [3 [4 5] 6] 7])
(p112 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])
(p112 9 (range))
(p112 1 [[[[[1]]]]])
(p112 0 [1 2 [3 [4 5] 6] 7])
(p112 0 [0 0 [0 [0]]])
(p112 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])