;p44: Rotate Sequence
;Write a function which can rotate a sequence in either direction
;
(defn rotate
  "Rotates a sequence in either direction."
  [N col]
  (let [N (mod N (count col))]
    (concat (drop N col) (take N col))))

(= (rotate 2 [1 2 3 4 5]) '(3 4 5 1 2))
(= (rotate -2 [1 2 3 4 5]) '(4 5 1 2 3))
(= (rotate 6 [1 2 3 4 5]) '(2 3 4 5 1))
(= (rotate -4 '(:a :b :c)) '(:c :a :b))
