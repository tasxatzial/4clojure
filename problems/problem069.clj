;p69: Merge with a Function
;Write a function which takes a function f and a variable number of maps. Your function should return a map that
;consists of the rest of the maps conj-ed onto the first. If a key occurs in more than one map, the mapping(s) from
;the latter (left-to-right) should be combined with the mapping in the result by calling (f val-in-result val-in-latter)
(def p69 (fn [& args]
           (reduce (fn [result x]
                     (conj result (reduce (fn [result2 x2]
                                     (if (contains? result (x2 0))
                                       (into result2 {(x2 0) ((first args) (result (x2 0)) (x2 1))})
                                       (conj result2 x2)))
                                   {} x)))
                   (second args) (nnext args))))

;tests
(p69 * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
(p69 - {1 10, 2 20} {1 3, 2 10, 3 15})
(p69 concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})