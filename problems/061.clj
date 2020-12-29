;p61: Map Construction
;Write a function which takes a vector of keys and a vector of values and constructs a map from them
;
;restrictions: zipmap
;
(defn my-zipmap
  "Takes a vector of keys and a vector of values and constructs a map from them."
  [col1 col2]
  (into {} (map #(vector %1 %2) col1 col2)))

;tests
(= (my-zipmap [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})
(= (my-zipmap [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"})
