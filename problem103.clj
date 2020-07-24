;p103: Generating k-combinations
;Given a sequence S consisting of n elements generate all k-combinations of S, i. e. generate all possible sets
;consisting of k distinct elements taken from S. The number of k-combinations for a sequence is equal to
;the binomial coefficient
(def p103 (fn [N col]
            (let [res (conj (reduce (fn [result x]
                                      (into result (conj (reduce (fn [result2 x2]
                                                                   (conj result2 (conj x2 x)))
                                                                 #{} result)
                                                         #{x})))
                                    #{} col)
                            #{})]
              (set (filter #(= N (count %)) res)))))

;tests
(p103 2 #{4 5 6})
(p103 10 #{4 5 6})
(p103 2 #{0 1 2})
(p103 3 #{0 1 2 3 4})
(p103 4 #{[1 2 3] :a "abc" "efg"})
(p103 2 #{[1 2 3] :a "abc" "efg"})