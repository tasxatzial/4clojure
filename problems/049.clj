;p49: Split a sequence
;Write a function which will split a sequence into two parts.
;
;restrictions: split-at
;
(defn my-split-at
  "Splits col into two parts, first part has exactly N elements."
  [N col]
  (vector (take N col) (drop N col)))

;tests
(= (my-split-at 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
(= (my-split-at 1 [:a :b :c :d]) [[:a] [:b :c :d]])
(= (my-split-at 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])
