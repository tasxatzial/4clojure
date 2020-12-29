;p22: Count a Sequence
;Write a function which returns the total number of elements in a sequence
;
;restrictions: count
;
(defn my-count
  "Returns the total number of elements in col."
  [col]
  (reduce (fn [total el]
            (inc total))
          0
          col))

;tests
(= (my-count '(1 2 3 3 1)) 5)
(= (my-count "Hello World") 11)
(= (my-count [[1 2] [3 4] [5 6]]) 3)
(= (my-count '(13)) 1)
(= (my-count '(:a :b :c)) 3)
