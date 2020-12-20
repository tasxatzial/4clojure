;p24: Sum It All Up
;Write a function which returns the sum of a sequence of numbers
(def p24 (fn [x]
           (apply + x)))

;tests
(map p24 [[1 2 3] (list 0 -2 5 5) #{4 2 1} '(0 0 -1) '(1 10 3)])