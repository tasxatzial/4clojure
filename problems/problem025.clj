;p25: Find the odd numbers
;Write a function which returns only the odd numbers from a sequence
;
(defn find-odd
  "Returns a col that consists of the odd numbers only."
  [col]
  (filter #(= 1 (mod % 2)) col))
