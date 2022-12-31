;; p61: Map Construction

;; Write a function which takes a vector of keys and a vector of values and constructs
;; a map from them.
;; restrictions: zipmap

(defn zip-map
  [col1 col2]
  (into {} (map #(vector %1 %2) col1 col2)))

(deftest tests
  (testing "test1"
    (is (= (zip-map [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})))
  (testing "test2"
    (is (= (zip-map [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"}))))
