;p25: Find the odd numbers
;Write a function which returns only the odd numbers from a sequence
(def p25 (fn [col]
           (filter #(= 1 (mod % 2)) col)))

;tests
(map p25 [#{1 2 3 4 5} [4 2 1 6] [2 2 4 6] [1 1 1 3]])