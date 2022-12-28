;; p23: Reverse a Sequence

;; Write a function which reverses a sequence
;; restrictions: reverse, rseq

(ns p23.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn reverse-seq
  [xs]
  (into '() xs))

(deftest tests
  (testing "test1"
    (is (= (reverse-seq [1 2 3 4 5]) [5 4 3 2 1])))
  (testing "test2"
    (is (= (reverse-seq (sorted-set 5 7 2 7)) '(7 5 2))))
  (testing "test3"
    (is (= (reverse-seq [[1 2] [3 4] [5 6]]) [[5 6] [3 4] [1 2]]))))
