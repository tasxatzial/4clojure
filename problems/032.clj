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

;tests
(= (duplicate1 [1 2 3]) '(1 1 2 2 3 3))
(= (duplicate1 [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (duplicate1 [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
(= (duplicate1 [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

(= (duplicate2 [1 2 3]) '(1 1 2 2 3 3))
(= (duplicate2 [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (duplicate2 [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
(= (duplicate2 [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
