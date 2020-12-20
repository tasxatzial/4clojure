;p22: Count a Sequence
;Write a function which returns the total number of elements in a sequence
;
;restrictions: count
;
(def p22 (fn [col]
           (reduce (fn [new-val x]
                     (inc new-val))
                   0 col)))

;tests
(map p22 ['(1 2 3 3 1) "Hello World" [[1 2] [3 4] [5 6]] '(13) '(:a :b :c)])