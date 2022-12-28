;; p49: Split a sequence

;; Write a function which will split a sequence into two parts.
;; restrictions: split-at

(ns p49.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn split-seq-at
  [N xs]
  (vector (take N xs) (drop N xs)))

(deftest tests
  (testing "test1"
    (is (= (split-seq-at 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])))
  (testing "test2"
    (is (= (split-seq-at 1 [:a :b :c :d]) [[:a] [:b :c :d]])))
  (testing "test3"
    (is (= (split-seq-at 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]]))))
