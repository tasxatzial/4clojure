;p110: Sequence of pronunciations
;Write a function that returns a lazy sequence of "pronunciations" of a sequence
;of numbers. A pronunciation of each element in the sequence consists of the number
;of repeating identical numbers and the number itself. For example, [1 1] is pronounced
;as [2 1] ("two ones"), which in turn is pronounced as [1 2 1 1] ("one two, one one").
;Your function should accept an initial sequence of numbers, and return an infinite lazy
;sequence of pronunciations, each element being a pronunciation of the previous element
;
(defn pronounce
  "Returns the pronunciation of a sequence of numbers."
  [col]
  (reduce (fn [result x]
            (conj result (count x) (first x)))
          []
          (partition-by identity col)))

(defn gen-pronounce-seq
  "Returns a lazy sequence of \"pronunciations\" of a sequence of numbers."
  [col]
  (lazy-seq (cons (pronounce col) (gen-pronounce-seq (pronounce col)))))

;tests
(= [[1 1] [2 1] [1 2 1 1]] (take 3 (gen-pronounce-seq [1])))
(= [3 1 2 4] (first (gen-pronounce-seq [1 1 1 4 4])))
(= [1 1 1 3 2 1 3 2 1 1] (nth (gen-pronounce-seq [1]) 6))
(= 338 (count (nth (gen-pronounce-seq [3 2]) 15)))
