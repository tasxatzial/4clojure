;p49: Split a sequence
;Write a function which will split a sequence into two parts
(def p49 (fn [N col]
           [(apply vector (take N col)) (apply vector (drop N col))]))

;tests
(p49 3 [1 2 3 4 5 6])
(p49 1 [:a :b :c :d])
(p49 2 [[1 2] [3 4] [5 6]])