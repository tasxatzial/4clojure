;; p99: Product Digits

;; Write a function which multiplies two numbers and returns the result as a sequence
;; of its digits.

(ns p99.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn product-digits
  [x y]
  (->> (*' x y)
       str
       (map (comp read-string str))))

(defn product-digits2
  [x y]
  (->> (*' x y)
       str
       (map #(Character/digit ^char % 10))))

(deftest tests-product-digits
  (testing "test1"
    (is (= (product-digits 1 1) [1])))
  (testing "test2"
    (is (= (product-digits 99 9) [8 9 1])))
  (testing "test3"
    (is (= (product-digits 999 99) [9 8 9 0 1]))))

(deftest tests-product-digits2
  (testing "test1"
    (is (= (product-digits2 1 1) [1])))
  (testing "test2"
    (is (= (product-digits2 99 9) [8 9 1])))
  (testing "test3"
    (is (= (product-digits2 999 99) [9 8 9 0 1]))))
