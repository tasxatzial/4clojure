;p56: Find Distinct Items
;Write a function which removes the duplicates from a sequence. Order of the items must be maintained
;
;restrictions: distinct
;
(def p56 (fn [col]
           (reduce (fn [result x]
                     (if ((set result) x)
                       result
                       (conj result x)))
                   [] col)))

;tests
(p56 [1 2 1 3 1 2 4])
(p56 [:a :a :b :b :c :c])
(p56 '([2 4] [1 2] [1 3] [1 3]))
(p56 (range 50))