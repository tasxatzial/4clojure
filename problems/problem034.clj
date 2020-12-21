;p34: Implement range
;Write a function which creates a list of all integers in a given range
;
;restrictions: range
;
(defn my-range
  "Creates a list of all integers in a given range."
  [N1 N2]
  (when (< N1 N2)
    (cons N1 (my-range (inc N1) N2))))
