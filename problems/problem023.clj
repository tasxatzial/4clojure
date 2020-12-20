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
