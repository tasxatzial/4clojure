;p88: Symmetric Difference
;Write a function which returns the symmetric difference of two sets. The symmetric difference is the set of items
;belonging to one but not both of the two sets

;solution 1 ----------------------------------------
(def p88 (fn [col1 col2]
           (reduce (fn [result x]
                     (if (or (and (contains? col1 x) (not (contains? col2 x)))
                             (and (contains? col2 x) (not (contains? col1 x))))
                       (conj result x)
                       result))
                   #{} (into col1 col2))))

;solution 1 tests
(p88 #{1 2 3 4 5 6} #{1 3 5 7})
(p88 #{:a :b :c} #{})
(p88 #{} #{4 5 6})
(p88 #{[1 2] [2 3]} #{[2 3] [3 4]})

;solution 2 ----------------------------------------
(def p88_2 (fn [col1 col2]
             (reduce (fn [result x]
                       (if (not (contains? (clojure.set/intersection col1 col2) x))
                         (conj result x)
                         result))
                     #{} (into col1 col2))))

;solution 2 tests
(p88_2 #{1 2 3 4 5 6} #{1 3 5 7})
(p88_2 #{:a :b :c} #{})
(p88_2 #{} #{4 5 6})
(p88_2 #{[1 2] [2 3]} #{[2 3] [3 4]})