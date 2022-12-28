;; p32: Duplicate a Sequence

;; Write a function which duplicates each element of a sequence

(ns p32.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn duplicate-elements
  [xs]
  (reduce into [] (map #(vector % %) xs)))

(deftest tests
  (testing "test1"
    (is (= (duplicate-elements [1 2 3]) '(1 1 2 2 3 3))))
  (testing "test2"
    (is (= (duplicate-elements [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))))
  (testing "test3"
    (is (= (duplicate-elements [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))))
  (testing "test4"
    (is (= (duplicate-elements [44 33]) [44 44 33 33]))))
