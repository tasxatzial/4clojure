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
