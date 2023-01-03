;; p33: Replicate a Sequence

;; Write a function which replicates each element of a sequence a variable number of times.

(ns p33.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn replicate-elements
  [xs N]
  (apply concat (map #(take N (repeat %)) xs)))

(defn replicate-elements2
  [xs N]
  (apply interleave (take N (repeat xs))))

(deftest tests-replicate-elements
  (testing "test1"
    (is (= (replicate-elements [1 2 3] 2) '(1 1 2 2 3 3))))
  (testing "test2"
    (is (= (replicate-elements [:a :b] 4) '(:a :a :a :a :b :b :b :b))))
  (testing "test3"
    (is (= (replicate-elements [4 5 6] 1) '(4 5 6))))
  (testing "test4"
    (is (= (replicate-elements [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))))
  (testing "test5"
    (is (= (replicate-elements [44 33] 2) [44 44 33 33]))))

(deftest tests-replicate-elements2
  (testing "test1"
    (is (= (replicate-elements2 [1 2 3] 2) '(1 1 2 2 3 3))))
  (testing "test2"
    (is (= (replicate-elements2 [:a :b] 4) '(:a :a :a :a :b :b :b :b))))
  (testing "test3"
    (is (= (replicate-elements2 [4 5 6] 1) '(4 5 6))))
  (testing "test4"
    (is (= (replicate-elements2 [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))))
  (testing "test5"
    (is (= (replicate-elements2 [44 33] 2) [44 44 33 33]))))
