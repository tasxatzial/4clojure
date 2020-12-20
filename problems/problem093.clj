;p93: Partially Flatten a Sequence
;Write a function which flattens any nested combination of sequential things (lists, vectors, etc.), but
;maintains the lowest level sequential items. The result should be a sequence of sequences with only one level of nesting
(def p93 (fn my-flatten [col]
           (reduce (fn [result x]
                     (into result
                           (if (sequential? x)
                             (if (sequential? (first x))
                               (my-flatten x)
                               [x])
                             [x])))
                   [] col)))

;tests
(p93  [["Do"] ["Nothing"]])
(p93 [[[[:a :b]]] [[:c :d]] [:e :f]])
(p93 '((1 2)((3 4)((((5 6)))))))