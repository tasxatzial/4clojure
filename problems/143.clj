;p143: dot product
;Create a function that computes the dot product of two sequences. You may assume that the
;vectors will have the same length
;
 (defn compute-dot-product
   [col1 col2]
   (reduce + (map * col1 col2)))

(= 0 (compute-dot-product [0 1 0] [1 0 0]))
(= 3 (compute-dot-product [1 1 1] [1 1 1]))
(= 32 (compute-dot-product [1 2 3] [4 5 6]))
(= 256 (compute-dot-product [2 5 6] [100 10 1]))
