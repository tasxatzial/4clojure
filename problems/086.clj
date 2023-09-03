;p86: Happy numbers

;; Happy numbers are positive integers that follow a particular formula: take each
;; individual digit, square it, and then sum the squares to get a new number. Repeat
;; with the new number and eventually, you might get to a number whose squared sum
;; is 1. This is a happy number. An unhappy number (or sad number) is one that loops
;; endlessly.
;; Write a function that determines if a number is happy or not.

(ns p86.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-digits
  [N]
  (map #(Character/digit ^char % 10) (str N)))

(defn squared-digit-sum
  "Returns the sum of the squares of the digits of N."
  [N]
  (let [digits (get-digits N)]
    (reduce + (map * digits digits))))

(defn happy?
  [N]
  (loop [N N
         result #{}]
    (cond
      (= 1 N) true
      (contains? result N) false
      :else (recur (squared-digit-sum N) (conj result N)))))

(deftest tests
  (testing "test1"
    (is (= (happy? 7) true)))
  (testing "test2"
    (is (= (happy? 986543210) true)))
  (testing "test3"
    (is (= (happy? 2) false)))
  (testing "test4"
    (is (= (happy? 3) false))))
