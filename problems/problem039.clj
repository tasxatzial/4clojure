;p39: Interleave Two Seqs
;Write a function which takes two sequences and returns the first item from each, then the second item from each,
;then the third, etc
;
;restrictions: interleave
;
(defn my-interleave
  "Interleaves two sequences."
  [col1 col2]
  (reduce concat (map #(vector %1 %2) col1 col2)))
