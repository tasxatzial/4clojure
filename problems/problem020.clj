;p20: Penultimate element
;Write a function which returns the second to last element from a sequence
;
(defn second-to-last
  "Returns the second to last element in col."
  [col]
  (if (>= (count col) 2)
    (nth col (- (count col) 2))
    nil))
