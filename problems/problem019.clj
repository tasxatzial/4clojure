;p19: last element
;Write a function which returns the last element in a sequence
;
;restrictions: last
;
(defn my-last
  "Returns the last element in col."
  [col]
  (if (empty? col)
    nil
    (nth col (- (count col) 1))))
