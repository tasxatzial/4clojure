;p33: Replicate a Sequence
;Write a function which replicates each element of a sequence a variable number of times
(def p33 (fn [col N]
           (reduce (fn [result x]
                     (concat result (take N (repeat x))))
                   '() col)))

;tests
(p33 [1 2 3] 2)
(p33 [:a :b] 4)
(p33 [4 5 6] 1)
(p33 [[1 2] [3 4]] 2)
(p33 [44 33] 2)