;p24: Sum It All Up
;Write a function which returns the sum of a sequence of numbers
;
(defn my-sum
  "Returns the sum of a sequence of numbers."
  [col]
  (apply + col))

(= (my-sum [1 2 3]) 6)
(= (my-sum (list 0 -2 5 5)) 8)
(= (my-sum #{4 2 1}) 7)
(= (my-sum '(0 0 -1)) -1)
(= (my-sum '(1 10 3)) 14)
