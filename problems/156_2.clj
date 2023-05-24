;; p156: Map Defaults

;; When retrieving values from a map, you can specify default values in case
;; the key is not found: (= 2 (:foo {:bar 0, :baz 1} 2))
;; However, what if you want the map itself to contain the default values?
;; Write a function which takes a default value and a sequence of keys and
;; constructs a map.

(ns p156.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn create-map
  [val xs]
  (->> (repeat val)
       (interleave xs)
       (apply hash-map)))

(deftest tests
  (testing "test1"
    (is (= (create-map 0 [:a :b :c]) {:a 0 :b 0 :c 0})))
  (testing "test2"
    (is (= (create-map "x" [1 2 3]) {1 "x" 2 "x" 3 "x"})))
  (testing "test3"
    (is (= (create-map [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]}))))
