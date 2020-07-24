;p63: Group a Sequence
;Given a function f and a sequence s, write a function which returns a map.
;The keys should be the values of f applied to each item in s.
;The value at each key should be a vector of corresponding items in the order they appear in s
;
;restrictions: group-by
;
(def p63 (fn [func col]
            (reduce (fn [result x]
                      (if (contains? result (x 1))
                        (into result {(x 1) (conj (result (x 1)) (x 0))} )
                        (into result {(x 1) [(x 0)]})))
                    {} (into [] (map (fn [x]
                                       [x, (func x)])
                                     col)))))

;tests
(p63 #(> % 5) [1 3 6 8])
(p63 #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
(p63 count [[1] [1 2] [3] [1 2 3] [2 3]])