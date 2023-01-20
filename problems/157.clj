;; p157: Indexing Sequences

;; Transform a sequence into a sequence of pairs containing the original elements
;; along with their index.

(ns p157.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn indexed-sequence1
  [coll]
  (partition 2 (interleave coll (range))))

(defn indexed-sequence2
  [coll]
  (keep-indexed #(vector %2 %1) coll))

(deftest tests-create-map1
  (testing "test1"
    (is (= (indexed-sequence1 [:a :b :c]) [[:a 0] [:b 1] [:c 2]])))
  (testing "test2"
    (is (= (indexed-sequence1 [0 1 3]) '((0 0) (1 1) (3 2)))))
  (testing "test3"
    (is (= (indexed-sequence1 [[:foo] {:bar :baz}]) [[[:foo] 0] [{:bar :baz} 1]]))))

(deftest tests-create-map2
  (testing "test1"
    (is (= (indexed-sequence2 [:a :b :c]) [[:a 0] [:b 1] [:c 2]])))
  (testing "test2"
    (is (= (indexed-sequence2 [0 1 3]) '((0 0) (1 1) (3 2)))))
  (testing "test3"
    (is (= (indexed-sequence2 [[:foo] {:bar :baz}]) [[[:foo] 0] [{:bar :baz} 1]]))))
