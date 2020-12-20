;p120: Sum of square of digits
;Write a function which takes a collection of integers as an argument. Return the count of how many elements are
;smaller than the sum of their squared component digits. For example: 10 is larger than 1 squared plus 0 squared;
;whereas 15 is smaller than 1 squared plus 5 squared
(def p120 (fn [col]
            (letfn [(toDigits [n]
                      (map (comp read-string str) (str n)))
                    (sumSqrd [num]
                      (reduce (fn [result x]
                                (+ result (* x x)))
                              0 (toDigits num)))]
              (count (filter #(< % (sumSqrd %)) col)))))

;tests
(p120 (range 10))
(p120 (range 30))
(p120 (range 100))
(p120 (range 1000))