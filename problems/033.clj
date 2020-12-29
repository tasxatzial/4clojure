;p33: Replicate a Sequence
;Write a function which replicates each element of a sequence a variable number of times

;solution 1
(defn my-replicate1
  "Replicates each element of a sequence a variable number of times."
  [col N]
  (reduce (fn [result x]
            (concat result (take N (repeat x))))
          '()
          col))

;solution 2
(defn my-replicate2
  "Replicates each element of a sequence a variable number of times."
  [col N]
  (apply concat (map #(take N (repeat %)) col)))

;tests
(= (my-replicate1 [1 2 3] 2) '(1 1 2 2 3 3))
(= (my-replicate1 [:a :b] 4) '(:a :a :a :a :b :b :b :b))
(= (my-replicate1 [4 5 6] 1) '(4 5 6))
(= (my-replicate1 [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
(= (my-replicate1 [44 33] 2) [44 44 33 33])

(= (my-replicate2 [1 2 3] 2) '(1 1 2 2 3 3))
(= (my-replicate2 [:a :b] 4) '(:a :a :a :a :b :b :b :b))
(= (my-replicate2 [4 5 6] 1) '(4 5 6))
(= (my-replicate2 [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
(= (my-replicate2 [44 33] 2) [44 44 33 33])
