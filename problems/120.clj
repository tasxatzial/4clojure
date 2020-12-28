;p120: Sum of square of digits
;Write a function which takes a collection of integers as an argument.
;Return the count of how many elements are smaller than the sum of their squared
;component digits. For example: 10 is larger than 1 squared plus 0 squared;
;whereas 15 is smaller than 1 squared plus 5 squared
;
(defn to-digits
  "Returns a list of the digits of a number."
  [N]
  (map (comp read-string str) (str N)))

(defn squared-digit-sum
  "Returns the sum of the squares of the digits of num."
  [num]
  (reduce (fn [result x]
            (+ result (* x x)))
          0 (to-digits num)))

(defn p120
  [col]
  (count (filter #(< % (squared-digit-sum %)) col)))

(= 8 (p120 (range 10)))
(= 19 (p120 (range 30)))
(= 50 (p120 (range 100)))
(= 50 (p120 (range 1000)))
