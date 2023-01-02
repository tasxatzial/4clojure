;; p34: Implement range

;; Write a function which creates a list of all integers in a given range
;; restrictions: range

(ns p34.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-range
  [n1 n2]
  (lazy-seq 
    (when (< n1 n2) 
      (cons n1 (get-range (inc n1) n2)))))

(defn get-range2
  [n1 n2]
  (loop [result []
         n1 n1]
    (if (< n1 n2)
      (recur (conj result n1) (inc n1))
      result)))

(deftest tests-get-range
  (testing "test1"
    (is (= (get-range 1 4) '(1 2 3))))
  (testing "test2"
    (is (= (get-range -2 2) '(-2 -1 0 1))))
  (testing "test3"
    (is (= (get-range 5 8) '(5 6 7)))))

(deftest tests-get-range2
  (testing "test1"
    (is (= (get-range2 1 4) '(1 2 3))))
  (testing "test2"
    (is (= (get-range2 -2 2) '(-2 -1 0 1))))
  (testing "test3"
    (is (= (get-range2 5 8) '(5 6 7)))))
