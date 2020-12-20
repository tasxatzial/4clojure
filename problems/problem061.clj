;p61: Map Construction
;Write a function which takes a vector of keys and a vector of values and constructs a map from them
(def p61 (fn [col1 col2]
           (into {} (map (fn [x1 x2]
                           [x1 x2])
                         col1 col2))))

;tests
(p61 [:a :b :c] [1 2 3])
(p61 [1 2 3 4] ["one" "two" "three"])
(p61 [:foo :bar] ["foo" "bar" "baz"])