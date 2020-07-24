;p131: Sum Some Set Subsets
;Given a variable number of sets of integers, create a function which returns true iff all of the sets have a
;non-empty subset with an equivalent summation
(def p131 (fn [& col]
            (letfn [(power-set [col]                        ;generate power set of col
                      (conj (reduce (fn [result x]
                                      (into result (conj (reduce (fn [result2 x2]
                                                                   (conj result2 (conj x2 x)))
                                                                 #{} result)
                                                         #{x})))
                                    #{} col)
                            #{}))
                    (sum-set [col]                          ;sum all terms in a subset and collect all sums of col
                      (reduce (fn [result x]
                                (if (empty? x)
                                  result
                                  (conj result (reduce + x))))
                              #{} col))]
              (let [sums (reduce (fn [result x]             ;collect all sums for each col
                                   (conj result ((comp sum-set power-set) x)))
                                 [] col)
                    min-terms-memo (memoize (fn [] (sort-by count sums)))]
                ((fn [col res]
                   (if (empty? col)
                     res
                     (if (some false? (map #(contains? % (first col)) (rest (min-terms-memo))))
                       (recur (rest col) false)
                       true)))
                 (first (min-terms-memo)) false)))))

;tests
(p131 #{-1 1 99}
      #{-2 2 888}
      #{-3 3 7777})
(p131 #{1}
      #{2}
      #{3}
      #{4})
(p131 #{1})
(p131 #{1 3 5}
      #{9 11 4}
      #{-3 12 3}
      #{-3 4 -2 10})
(p131 #{-1 -2 -3 -4 -5 -6}
      #{1 2 3 4 5 6 7 8 9})
(p131 #{1 3 5 7}
      #{2 4 6 8})
(p131 #{-1 3 -5 7 -9 11 -13 15}
      #{1 -3 5 -7 9 -11 13 -15}
      #{1 -1 2 -2 4 -4 8 -8})
(p131 #{-10 9 -8 7 -6 5 -4 3 -2 1}
      #{10 -9 8 -7 6 -5 4 -3 2 -1})