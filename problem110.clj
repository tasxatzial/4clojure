;p110: Sequence of pronunciations
;Write a function that returns a lazy sequence of "pronunciations" of a sequence of numbers. A pronunciation of each
;element in the sequence consists of the number of repeating identical numbers and the number itself.
;For example, [1 1] is pronounced as [2 1] ("two ones"), which in turn is pronounced as [1 2 1 1] ("one two, one one").
;Your function should accept an initial sequence of numbers, and return an infinite lazy sequence of pronunciations,
;each element being a pronunciation of the previous element
(def p110 (fn [col]
            (letfn [(p110_S [col]
                      (reduce (fn [result x]
                                (conj result (count x) (first x)))
                              [] (partition-by identity col)))]
              ((fn my-sp [col]
                 (lazy-seq (cons (p110_S col) (my-sp (p110_S col)))))
               col))))

;tests
(take 3 (p110 [1]))
(first (p110 [1 1 1 4 4]))
(nth (p110 [1]) 6)
(count (nth (p110 [3 2]) 15))