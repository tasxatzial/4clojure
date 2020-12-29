;p55: Count Occurrences
;Write a function which returns a map containing the number of occurrences of each distinct item in a sequence
;
;restrictions: frequencies
;
(defn my-frequencies
  "Returns a map containing the number of occurrences of each distinct item in a sequence."
  [col]
  (reduce (fn [result x]
            (if (result x)
              (assoc result x (inc (result x)))
              (conj result x 1)))
          {}
          col))

;tests
(= (my-frequencies [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})
(= (my-frequencies [:b :a :b :a :b]) {:a 2, :b 3})
(= (my-frequencies '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})
