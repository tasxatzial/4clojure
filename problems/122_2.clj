;; p122: Read a binary number

;; Convert a binary number, provided in the form of a string, to its numerical value.

(ns p122.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn bin-char->dec
  "Returns the decimal value of a char that represents a binary digit."
  [c]
  (- (int c) 48))

(defn pow2n
  "Returns 2^n."
  [n]
  (reduce * (repeat n 2)))

(defn powers2n
  "Returns a seq of the powers of 2 from 2^n to 2^0."
  [n]
  (map #(pow2n %) (range n -1 -1)))

(defn to-decimal
  [s]
  (let [decimal-values (map bin-char->dec s)
        powers (powers2n (dec (count decimal-values)))]
    (reduce + (map * decimal-values powers))))

(deftest tests
  (testing "test1"
    (is (= 0     (to-decimal "0"))))
  (testing "test2"
    (is (= 7     (to-decimal "111"))))
  (testing "test3"
    (is (= 8     (to-decimal "1000"))))
  (testing "test4"
    (is (= 9     (to-decimal "1001"))))
  (testing "test5"
    (is (= 255   (to-decimal "11111111"))))
  (testing "test6"
    (is (= 1365  (to-decimal "10101010101"))))
  (testing "test7"
    (is (= 65535 (to-decimal "1111111111111111")))))
