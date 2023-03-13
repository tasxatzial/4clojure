;; p44: Rotate Sequence

;; Write a function which can rotate a sequence in either direction.

(ns p44.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn rotate
  [N xs]
  (let [split-index (mod N (count xs))
        split-xs (split-at split-index xs)]
    (concat (second split-xs) (first split-xs))))

(deftest tests
  (testing "test1"
    (is (= (rotate 2 [1 2 3 4 5]) '(3 4 5 1 2))))
  (testing "test2"
    (is (= (rotate -2 [1 2 3 4 5]) '(4 5 1 2 3))))
  (testing "test3"
    (is (= (rotate 6 [1 2 3 4 5]) '(2 3 4 5 1))))
  (testing "test4"
    (is (= (rotate -4 '(:a :b :c)) '(:c :a :b)))))
