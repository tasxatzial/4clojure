;p44: Rotate Sequence
;Write a function which can rotate a sequence in either direction
;
;example: (f 2 [1 2 3 4 5]) -> '(3 4 5 1 2)
;example: (f -2 [1 2 3 4 5]) -> '(4 5 1 2 3)
;example: (f 6 [1 2 3 4 5]) -> '(2 3 4 5 1)
;
(defn rotate
  "Rotates a sequence in either direction."
  [N col]
  (let [N (mod N (count col))]
    (concat (drop N col) (take N col))))
