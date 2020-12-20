;p147: Pascal's Trapezoid
;Write a function that, for any given input vector of numbers, returns an infinite lazy sequence of vectors, where
;each next one is constructed from the previous following the rules used in Pascal's Triangle. For example,
;for [3 1 2], the next row is [3 4 3 2].
;Beware of arithmetic overflow! In clojure (since version 1.3 in 2011), if you use an arithmetic operator like + and
; the result is too large to fit into a 64-bit integer, an exception is thrown. You can use +' to indicate that you
;would rather overflow into Clojure's slower, arbitrary-precision bigint

;solution 1 ----------------------------------------
(def p147 (fn [col]
            (letfn [(newrow [col]
                      (let [middle ((fn [result col]
                                      (if (= 1 (count col))
                                        result
                                        (recur (conj result (+' (first col) (second col))) (rest col))))
                                    [] col)]
                        (conj (into [(first col)] middle) (last col))))]
              ((fn pascal [col]
                 (lazy-seq (cons col (pascal (newrow col)))))
               col))))

;solution 1 tests
(second (p147 [2 3 2]))
(take 5 (p147 [1]))
(take 2 (p147 [3 1 2]))
(take 100 (p147 [2 4 2]))

;solution 2 ----------------------------------------
(def p147_2 (fn [col]
            (letfn [(newrow [col]
                      (let [middle (map (fn [x1 x2]
                                          (+' x1 x2)) col (rest col))]
                        (conj (into [(first col)] middle) (last col))))]
              ((fn pascal [col]
                 (lazy-seq (cons col (pascal (newrow col)))))
               col))))

;solution 2 tests
(second (p147_2 [2 3 2]))
(take 5 (p147_2 [1]))
(take 2 (p147_2 [3 1 2]))
(take 100 (p147_2 [2 4 2]))