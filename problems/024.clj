;; p24: Sum It All Up

;; Write a function which returns the sum of a sequence of numbers.

(ns p24.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn sum
  [xs]
  (apply + xs))

(deftest tests
  (testing "test1"
    (is (= (sum [1 2 3]) 6)))
  (testing "test2"
    (is (= (sum (list 0 -2 5 5)) 8)))
  (testing "test3"
    (is (= (sum #{4 2 1}) 7)))
  (testing "test4"
    (is (= (sum '(0 0 -1)) -1)))
  (testing "test5"
    (is (= (sum '(1 10 3)) 14))))
