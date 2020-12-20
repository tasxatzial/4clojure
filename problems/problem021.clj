;p21: nth element
;Write a function which returns the Nth element from a sequence
;
;restrictions: nth
;
(def p21 (fn my-nth [col n]
           (if (= n 0)
             (first col)
             (recur (rest col) (- n 1)))))

;tests
(map p21 ['(4 5 6 7) [:a :b :c] [1 2 3 4] '([1 2] [3 4] [5 6])] [2 0 1 2])