;; p115: The Balance of N

;; A balanced number is one whose component digits have the same sum on the
;; left and right halves of the number. Write a function which accepts an
;; integer n, and returns true iff n is balanced.

(ns p115.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn to-digits
  "Returns a list of the digits of a number."
  [N]
  (->> N
       str
       (map #(Character/digit ^char % 10))))

(defn balanced?
  [N]
  (let [digits (to-digits N)
        half (/ (count digits) 2)
        sum-left-half (reduce + (take half digits))
        sum-right-half (reduce + (take-last half digits))]
    (= sum-left-half sum-right-half)))

(deftest tests
  (testing "test1"
    (is (= true (balanced? 11))))
  (testing "test2"
    (is (= true (balanced? 121))))
  (testing "test3"
    (is (= false (balanced? 123))))
  (testing "test4"
    (is (= true (balanced? 0))))
  (testing "test5"
    (is (= false (balanced? 88099))))
  (testing "test6"
    (is (= true (balanced? 89098))))
  (testing "test7"
    (is (= true (balanced? 89089))))
  (testing "test8"
    (is (= (take 20 (filter balanced? (range)))
           [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101]))))
