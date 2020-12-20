;p55: Count Occurrences
;Write a function which returns a map containing the number of occurrences of each distinct item in a sequence
;
;restrictions: frequencies
;
(def p55 (fn [col]
            (reduce (fn [result x]
                      (if (result x)
                        (conj result [x (+ 1 (result x))])
                        (conj result [x 1])))
                    {} col)))

;tests
(p55 [1 1 2 3 2 1 1])
(p55 [:b :a :b :a :b])
(p55 '([1 2] [1 3] [1 3]))