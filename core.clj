(ns four-clojure.core)

;p19: last element
;Write a function which returns the last element in a sequence
;restrictions: last
(map #(if (empty? %)
        nil
        (nth % (- (count %) 1)))
     [[1 2 3 4 5] '(5 4 3) ["b" "c" "d"]])