;; p122: Read a binary number

;; Convert a binary number, provided in the form of a string, to its numerical value.

(ns p122.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn to-decimal
  [str]
  (loop [digits (seq str)
         result 0
         idx (dec (count digits))]
    (if digits
      (if (= \0 (first digits))
        (recur (next digits) result (dec idx))
        (let [pow2 (Math/round (Math/pow 2 idx))]
          (recur (next digits) (+' result pow2) (dec idx))))
      result)))

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
