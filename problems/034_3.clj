;; p34: Implement range

;; Write a function which creates a list of all integers in a given range.
;; restrictions: range

(ns p34.core
  (:require [clojure.test :refer [deftest testing is]]))

;; lazy
(defn get-range
  [n1 n2]
  (take (- n2 n1) (iterate inc n1)))

(deftest tests
  (testing "test1"
    (is (= (get-range 1 4) '(1 2 3))))
  (testing "test2"
    (is (= (get-range -2 2) '(-2 -1 0 1))))
  (testing "test3"
    (is (= (get-range 5 8) '(5 6 7)))))
