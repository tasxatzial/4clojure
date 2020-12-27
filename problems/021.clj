;p21: nth element
;Write a function which returns the Nth element from a sequence
;
;restrictions: nth
;
(defn my-nth
  "Returns the nth element in col."
  [col n]
  (if (= n 0)
    (first col)
    (recur (rest col) (- n 1))))

(= (my-nth '(4 5 6 7) 2) 6)
(= (my-nth [:a :b :c] 0) :a)
(= (my-nth [1 2 3 4] 1) 2)
(= (my-nth '([1 2] [3 4] [5 6]) 2) [5 6])
