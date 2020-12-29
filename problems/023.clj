;p23: Reverse a Sequence
;Write a function which reverses a sequence
;
;restrictions: reverse, rseq
;
(defn my-reverse
  "Reverses the order of elements in col."
  [col]
  (reduce (fn [result x]
            (conj result x))
          '()
          col))

;tests
(= (my-reverse [1 2 3 4 5]) [5 4 3 2 1])
(= (my-reverse (sorted-set 5 7 2 7)) '(7 5 2))
(= (my-reverse [[1 2] [3 4] [5 6]]) [[5 6] [3 4] [1 2]])
