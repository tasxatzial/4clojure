;p85: Power Set
;Write a function which generates the power set of a given set. The power set of a set x is the set of all
;subsets of x, including the empty set and x itself
(def p85 (fn [col]
           (conj (reduce (fn [result x]
                           (into result (conj (reduce (fn [result2 x2]
                                                        (conj result2 (conj x2 x)))
                                                      #{} result)
                                              #{x})))
                         #{} col)
                 #{})))

;tests
(p85 #{1 :a})
(p85 #{})
(p85 #{1 2 3})
(= (count (p85 (into #{} (range 10)))) 1024)