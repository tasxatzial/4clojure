;p99: Product Digits
;Write a function which multiplies two numbers and returns the result as a sequence of its digits
(def p99 (fn [x y]
           (map (comp read-string str) (str (* x y)))))

;tests
(p99 1 1)
(p99 99 9)
(p99 999 99)