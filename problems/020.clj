;p20: Penultimate element
;Write a function which returns the second to last element from a sequence
;
(defn second-to-last
  "Returns the second to last element in col."
  [col]
  (if (>= (count col) 2)
    (nth col (- (count col) 2))
    nil))

(= (second-to-last (list 1 2 3 4 5)) 4)
(= (second-to-last ["a" "b" "c"]) "b")
(= (second-to-last [[1 2] [3 4]]) [1 2])
