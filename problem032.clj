;p32: Duplicate a Sequence
;Write a function which duplicates each element of a sequence
(def p32 (fn [col]
           (reduce (fn [result x]
                     (concat result [x x]))
                   '() col)))

;tests
(map p32 [[1 2 3] [:a :a :b :b] [[1 2] [3 4]]])