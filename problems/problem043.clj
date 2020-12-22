;p43: Reverse Interleave
;Write a function which reverses the interleave process into x number of subsequences
;
;Assume that the total number of elements in the sequence is a multiple of x
;
;example: (f [1 2 3 4 5 6] 2) -> '((1 3 5) (2 4 6))
;example: (f (range 9) 3) -> '((0 3 6) (1 4 7) (2 5 8))

;solution 1
(defn rev-interleave1
  [col N]
  (let [partitioned (partition N col)
        interleaved (apply interleave partitioned)]
    (partition (count partitioned) interleaved)))

;solution 2
(defn rev-interleave2
  [col N]
  (apply map list (partition N col)))
