;p166: Sequence Reductions
;Write a function which behaves like reduce, but returns each intermediate value
;of the reduction. Your function must accept either two or three arguments, and
;the return sequence must be lazy.
;
;restrictions: reductions
;
(defn my-reductions
  "Behaves like reduce, but returns each intermediate value of the reduction."
  ([f col]
   (if (empty? col)
     (f)
     (my-reductions f (first col) (rest col))))
  ([f val col]
   (if (empty? col)
     (list val)
     (cons val (lazy-seq (my-reductions f (f val (first col)) (rest col)))))))

;tests
(= (take 5 (my-reductions + (range))) [0 1 3 6 10])
(= (my-reductions conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])
(= (last (my-reductions * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120)
