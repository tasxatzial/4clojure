;p38: Maximum value
;Write a function which takes a variable number of parameters and returns the maximum value
;
;restrictions: max, max-key

(defn my-max
  "Returns the maximum value."
  [& col]
  (reduce (fn [max x]
            (if (> x max)
              x
              max))
          (first col)
          (rest col)))

(= (my-max 1 8 3 4) 8)
(= (my-max 30 20) 30)
(= (my-max 45 67 11) 67)
