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
