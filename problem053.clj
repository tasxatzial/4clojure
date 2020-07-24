;p53: Longest Increasing Sub-Seq
;Given a vector of integers, find the longest consecutive sub-sequence of increasing numbers.
;If two sub-sequences have the same length, use the one that occurs first.
;An increasing sub-sequence must have a length of 2 or greater to qualify
(def p53 (fn [col]
           (reduce (fn [result x]
                     (if (> (count x) (count result))
                       x
                       result))
                   [] (filter #(> (count %) 1)
                              (reduce (fn [result x]
                                        (if (empty? result)
                                          (conj result [x])
                                          (if (> x (last (last result)))
                                            (conj (apply vector (butlast result)) (conj (apply vector (last result)) x))
                                            (conj result [x]))))
                                      [] col)))))

;tests
(p53 [1 0 1 2 3 0 4 5])
(p53 [5 6 1 3 2 7])
(p53 [2 3 3 4 5])
(p53 [7 6 5 4])