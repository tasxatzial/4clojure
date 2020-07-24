;p19: last element
;Write a function which returns the last element in a sequence
;
;restrictions: last
;
(def p19 #(if (empty? %)
            nil
            (nth % (- (count %) 1))))

;tests
(map p19 [[1 2 3 4 5] '(5 4 3) ["b" "c" "d"]])