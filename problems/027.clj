;; p27: Palindrome Detector

;; Write a function which returns true if the given sequence is a palindrome.

(ns p27.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn palindrome?
  [xs]
  (= (seq xs) (reverse xs)))

(deftest tests
  (testing "test1"
    (is (false? (palindrome? '(1 2 3 4 5)))))
  (testing "test2"
    (is (true? (palindrome? "racecar"))))
  (testing "test3"
    (is (true? (palindrome? [:foo :bar :foo]))))
  (testing "test4"
    (is (true? (palindrome? '(1 1 3 3 1 1)))))
  (testing "test5"
    (is (false? (palindrome? '(:a :b :c))))))
