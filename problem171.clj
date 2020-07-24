;p171: Intervals
;Write a function that takes a sequence of integers and returns a sequence of "intervals". Each interval is a a vector
;of two integers, start and end, such that all integers between start and end (inclusive) are contained in the input sequence
(def p171 (fn [col]
            (reduce (fn [result x]
                      (if (or (empty? result) (> x (inc (last (last result)))))
                        (conj result [x x])
                        (if (= x (inc (last (last result))))
                          (assoc result (- (count result) 1) (assoc (last result) (- (count (last result)) 1) x))
                          result)))
                    [] (sort col))))

;tests
(p171 [1 2 3])
(p171 [10 9 8 1 2 3])
(p171 [1 1 1 1 1 1 1])
(p171 [])
(p171 [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11])