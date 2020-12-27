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

(= (my-range 1 4) '(1 2 3))
(= (my-range -2 2) '(-2 -1 0 1))
(= (my-range 5 8) '(5 6 7))
