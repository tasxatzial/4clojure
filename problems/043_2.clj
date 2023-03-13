;; p43: Reverse Interleave

;; Write a function which reverses the interleave process into N number of subsequences.

(ns p43.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn reverse-interleave
  [xs N]
  (apply map list (partition N xs)))

(deftest tests
  (testing "test1"
    (is (= (reverse-interleave [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))))
  (testing "test2"
    (is (= (reverse-interleave (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))))
  (testing "test3"
    (is (= (reverse-interleave (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9))))))
