;p43: Reverse Interleave
;Write a function which reverses the interleave process into x number of subsequences
;
;Assume that the total number of elements in the sequence is a multiple of x
;

;solution 1
(defn rev-interleave1
  "Reverses the interleave process into x number of subsequences."
  [col N]
  (let [partitioned (partition N col)
        interleaved (apply interleave partitioned)]
    (partition (count partitioned) interleaved)))

;solution 2
(defn rev-interleave2
  "Reverses the interleave process into x number of subsequences."
  [col N]
  (apply map list (partition N col)))

(= (rev-interleave1 [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))
(= (rev-interleave1 (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))
(= (rev-interleave1 (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))
