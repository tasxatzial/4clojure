(ns four-clojure.core)

;p19: last element
;Write a function which returns the last element in a sequence
;restrictions: last
(map #(if (empty? %)
        nil
        (nth % (- (count %) 1)))
     [[1 2 3 4 5] '(5 4 3) ["b" "c" "d"]])


;p20: Penultimate element
;Write a function which returns the second to last element from a sequence
(map #(if (>= (count %) 2)
        (nth % (- (count %) 2))
        nil)
     [(list 1 2 3 4 5) ["a" "b" "c"] [[1 2] [3 4]]])