;; p42: Factorial Fun

;; Write a function which calculates factorials.

(ns p42.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn factorial
  [N]
  (reduce *' (range 1 (inc N))))

(deftest tests
  (testing "test1"
    (is (= (factorial 1) 1)))
  (testing "test2"
    (is (= (factorial 3) 6)))
  (testing "test3"
    (is (= (factorial 5) 120)))
  (testing "test4"
    (is (= (factorial 8) 40320))))
