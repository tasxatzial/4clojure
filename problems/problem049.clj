;p49: Split a sequence
;Write a function which will split a sequence into two parts.
;
;restrictions: split-at
(defn my-split-at
  "Splits col into two parts, first part has exactly N elements."
  [N col]
  (vector (take N col) (drop N col)))
