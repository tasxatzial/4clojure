;p156: Map Defaults
;When retrieving values from a map, you can specify default values in case the key is not found:
;(= 2 (:foo {:bar 0, :baz 1} 2))
;However, what if you want the map itself to contain the default values? Write a function which takes a default
;value and a sequence of keys and constructs a map

;solution 1 ----------------------------------------
(def p156 (fn [val col]
            (reduce (fn [result x]
                      (conj result [x val]))
                    {} col)))

;solution 1 tests
(p156 0 [:a :b :c])
(p156 "x" [1 2 3])
(p156 [:a :b] [:foo :bar])

;solution 2 ----------------------------------------
(def p156_2 (fn [val col]
            (apply hash-map (interleave col (repeat val)))))

;solution 2 tests
(p156_2 0 [:a :b :c])
(p156_2 "x" [1 2 3])
(p156_2 [:a :b] [:foo :bar])