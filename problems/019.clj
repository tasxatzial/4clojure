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

;tests
(= (my-last [1 2 3 4 5]) 5)
(= (my-last '(5 4 3)) 3)
(= (my-last ["b" "c" "d"]) "d")
