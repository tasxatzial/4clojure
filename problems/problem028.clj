;p28: Flatten a Sequence
;Write a function which flattens a sequence
;
;restrictions: flatten
;
(def p28 (fn my-flatten [col]
           (reduce (fn [result x]
                     (concat result
                             (if (sequential? x)
                               (my-flatten x)
                               (list x))))
                   '() col)))

;tests
(map p28 ['((1 2) 3 [4 [5 6]]) ["a" ["b"] "c"] '((((:a))))])