;p25: Find the odd numbers
;Write a function which returns only the odd numbers from a sequence
;
(defn find-odd
  "Returns a col that consists of the odd numbers only."
  [col]
  (filter #(= 1 (mod % 2)) col))

;tests
(= (find-odd #{1 2 3 4 5}) '(1 3 5))
(= (find-odd [4 2 1 6]) '(1))
(= (find-odd [2 2 4 6]) '())
(= (find-odd [1 1 1 3]) '(1 1 1 3))
