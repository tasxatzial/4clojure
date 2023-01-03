;; p42: Factorial Fun

;; Write a function which calculates factorials.

(ns p42.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn factorial
  [N]
  (apply *' (range 1 (inc N))))

(defn factorial2
  ([N]
   (if (zero? N)
     1
     (factorial2 N 1)))
  ([N result]
   (if (= N 1)
     result
     (recur (dec N) (*' result N)))))

(deftest tests-factorial
  (testing "test1"
    (is (= (factorial 1) 1)))
  (testing "test2"
    (is (= (factorial 3) 6)))
  (testing "test3"
    (is (= (factorial 5) 120)))
  (testing "test4"
    (is (= (factorial 8) 40320))))

(deftest tests-factorial2
  (testing "test1"
    (is (= (factorial2 1) 1)))
  (testing "test2"
    (is (= (factorial2 3) 6)))
  (testing "test3"
    (is (= (factorial2 5) 120)))
  (testing "test4"
    (is (= (factorial2 8) 40320))))
