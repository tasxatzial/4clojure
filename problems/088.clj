;p88: Symmetric Difference
;Write a function which returns the symmetric difference of two sets. The symmetric
;difference is the set of items belonging to one but not both of the two sets

(defn symmetric-difference
  "Returns the symmetric difference of set1 and set2."
  [set1 set2]
  (let [intersection (clojure.set/intersection set1 set2)]
    (reduce (fn [result x]
              (if (contains? intersection x)
                result
                (conj result x)))
            #{}
            (into set1 set2))))

;tests
(= (symmetric-difference #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})
(= (symmetric-difference #{:a :b :c} #{}) #{:a :b :c})
(= (symmetric-difference #{} #{4 5 6}) #{4 5 6})
(= (symmetric-difference #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]})
