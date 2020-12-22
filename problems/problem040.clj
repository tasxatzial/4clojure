;p40: Interpose a Seq
;Write a function which separates the items of a sequence by an arbitrary value
;
;restrictions: interpose
;
(defn my-interpose
  "Interposes a sequence by N."
  [N col]
  (butlast (apply concat (map #(vector % N) col))))
