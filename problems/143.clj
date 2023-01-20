;; p143: dot product

;; Create a function that computes the dot product of two sequences. You may assume
;; that the vectors will have the same length.

(ns p143.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn dot-product
  [coll1 coll2]
  (reduce + (map * coll1 coll2)))

(deftest tests
  (testing "test1"
    (is (= 0 (dot-product [0 1 0] [1 0 0]))))
  (testing "test2"
    (is (= 3 (dot-product [1 1 1] [1 1 1]))))
  (testing "test3"
    (is (= 32 (dot-product [1 2 3] [4 5 6]))))
  (testing "test4"
    (is (= 256 (dot-product [2 5 6] [100 10 1])))))
