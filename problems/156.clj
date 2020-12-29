;p156: Map Defaults
;When retrieving values from a map, you can specify default values in case the key is not found:
;(= 2 (:foo {:bar 0, :baz 1} 2))
;However, what if you want the map itself to contain the default values? Write a function which takes
;a default value and a sequence of keys and constructs a map

;solution 1
(defn create-map1
  "Constructs a map from val and a collection col of keys."
  [val col]
  (reduce (fn [result x]
            (conj result [x val]))
          {} col))

;solution 2
(defn create-map2
  "Constructs a map from val and a collection col of keys."
  [val col]
  (apply hash-map (interleave col (repeat val))))

(= (create-map1 0 [:a :b :c]) {:a 0 :b 0 :c 0})
(= (create-map1 "x" [1 2 3]) {1 "x" 2 "x" 3 "x"})
(= (create-map1 [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]})
