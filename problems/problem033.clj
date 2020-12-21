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
