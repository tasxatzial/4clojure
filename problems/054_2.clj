;; p54: Partition a Sequence

;; Write a function which returns a sequence of lists of N items each.
;; Lists of less than N items should not be returned.
;; restrictions: partition, partition-all

(ns p54.core
  (:require [clojure.test :refer [deftest testing is]]))

;; lazy
(defn partition-seq
  [N xs]
  (lazy-seq
    (let [first-N (take N xs)]
      (when (= N (count first-N))
        (cons first-N (partition-seq N (drop N xs)))))))

(deftest tests
  (testing "test1"
    (is (= (partition-seq 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))))
  (testing "test2"
    (is (= (partition-seq 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))))
  (testing "test3"
    (is (= (partition-seq 3 (range 8)) '((0 1 2) (3 4 5))))))
