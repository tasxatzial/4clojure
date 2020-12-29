;p93: Partially Flatten a Sequence
;Write a function which flattens any nested combination of sequential things (lists, vectors, etc.), but
;maintains the lowest level sequential items. The result should be a sequence of sequences with only one level of nesting
;
(defn my-partial-flatten
   "Partially flattens a sequence. Maintains the lowest level sequential items."
   [col]
   (reduce (fn [result x]
             (into result
                   (if (and (sequential? x) (sequential? (first x)))
                     (my-partial-flatten x)
                     [x])))
           [] col))

;tests
(= (my-partial-flatten [["Do"] ["Nothing"]])
   [["Do"] ["Nothing"]])
(= (my-partial-flatten [[[[:a :b]]] [[:c :d]] [:e :f]])
   [[:a :b] [:c :d] [:e :f]])
(= (my-partial-flatten '((1 2) ((3 4) ((((5 6)))))))
   '((1 2) (3 4) (5 6)))
