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
