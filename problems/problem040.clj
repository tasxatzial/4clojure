;p40: Interpose a Seq
;Write a function which separates the items of a sequence by an arbitrary value
;
;restrictions: interpose
;
(defn my-interpose
  "Interposes a sequence by N."
  [N col]
  (butlast (apply concat (map #(vector % N) col))))

(= (my-interpose 0 [1 2 3]) [1 0 2 0 3])
(= (apply str (my-interpose ", " ["one" "two" "three"])) "one, two, three")
(= (my-interpose :z [:a :b :c :d]) [:a :z :b :z :c :z :d])
