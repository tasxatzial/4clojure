;p32: Duplicate a Sequence
;Write a function which duplicates each element of a sequence

;solution 1
(defn duplicate1
  "Duplicates each element of a sequence."
  [col]
  (reduce (fn [result x]
            (concat result [x x]))
          '()
          col))

;solution 2
(defn duplicate2
  "Duplicates each element of a sequence."
  [col]
  (apply concat (map #(vector % %) col)))
