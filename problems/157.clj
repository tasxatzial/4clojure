;p157: Indexing Sequences
;Transform a sequence into a sequence of pairs containing the original elements along with their index

;solution 1 ----------------------------------------
(def p157 (fn [col]
            (partition 2 (interleave col (range)))))

;solution 1 tests
(p157 [:a :b :c])
(p157 [0 1 3])
(p157 [[:foo] {:bar :baz}])

;solution 2 ----------------------------------------
(def p157_2 (fn [col]
              ((fn [result col idx]
                 (if (empty? col)
                   result
                   (recur (conj result [(first col) idx]) (rest col) (+ idx 1))))
               [] col 0)))

;solution 2 tests
(p157_2 [:a :b :c])
(p157_2 [0 1 3])
(p157_2 [[:foo] {:bar :baz}])

;solution 3 ----------------------------------------
(def p157_3 (fn [col]
              (rest (reduce (fn [result x]
                              (conj result [x (inc (last (last result)))]))
                            [[-1]] col))))

;solution 3 tests
(p157_3 [:a :b :c])
(p157_3 [0 1 3])
(p157_3 [[:foo] {:bar :baz}])