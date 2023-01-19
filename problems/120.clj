;; p120: Sum of square of digits

;; Write a function which takes a collection of integers as an argument.
;; Return the count of how many elements are smaller than the sum of their squared
;; component digits. For example: 10 is larger than 1 squared plus 0 squared;
;; whereas 15 is smaller than 1 squared plus 5 squared.

(ns p120.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn to-digits
  "Returns a list of the digits of a number."
  [n]
  (->> n
       str
       (map #(Character/digit ^char % 10))))

(defn squared-digit-sum
  "Returns the sum of the squares of the digits a number."
  [n]
  (->> n
       to-digits
       (map #(* % %))
       (apply +)))

(defn p120
  [col]
  (->> col
       (filter #(< % (squared-digit-sum %)))
       count))

(deftest tests
  (testing "test1"
    (is (= 8 (p120 (range 10)))))
  (testing "test2"
    (is (= 19 (p120 (range 30)))))
  (testing "test3"
    (is (= 50 (p120 (range 100)))))
  (testing "test4"
    (is (= 50 (p120 (range 1000))))))
