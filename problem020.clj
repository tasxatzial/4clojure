;p20: Penultimate element
;Write a function which returns the second to last element from a sequence
(def p20 #(if (>= (count %) 2)
            (nth % (- (count %) 2))
            nil))

;tests
(map p20 [(list 1 2 3 4 5) ["a" "b" "c"] [[1 2] [3 4]]])