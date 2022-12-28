;; p25: Find the odd numbers

;; Write a function which returns only the odd numbers from a sequence

(ns p25.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn find-odd
  [xs]
  (filter odd? xs))

(deftest tests
  (testing "test1"
    (is (= (find-odd #{1 2 3 4 5}) '(1 3 5))))
  (testing "test2"
    (is (= (find-odd [4 2 1 6]) '(1))))
  (testing "test3"
    (is (= (find-odd [2 2 4 6]) '())))
  (testing "test4"
    (is (= (find-odd [1 1 1 3]) '(1 1 1 3)))))
