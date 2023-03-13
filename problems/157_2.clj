;; p157: Indexing Sequences

;; Transform a sequence into a sequence of pairs containing the original elements
;; along with their index.

(ns p157.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn indexed-sequence
  [coll]
  (keep-indexed #(vector %2 %1) coll))

(deftest tests
  (testing "test1"
    (is (= (indexed-sequence [:a :b :c]) [[:a 0] [:b 1] [:c 2]])))
  (testing "test2"
    (is (= (indexed-sequence [0 1 3]) '((0 0) (1 1) (3 2)))))
  (testing "test3"
    (is (= (indexed-sequence [[:foo] {:bar :baz}]) [[[:foo] 0] [{:bar :baz} 1]]))))
