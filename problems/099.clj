;p99: Product Digits
;Write a function which multiplies two numbers and returns the result as a sequence of its digits
;
(defn p99
  "Multiplies two numbers and returns the result as a sequence of its digits."
  [x y]
  (map (comp read-string str) (str (* x y))))

(= (p99 1 1) [1])
(= (p99 99 9) [8 9 1])
(= (p99 999 99) [9 8 9 0 1])
