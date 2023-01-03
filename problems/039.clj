;; p39: Interleave Two Seqs

;; Write a function which takes two sequences and returns the first item from each,
;; then the second item from each, then the third, etc.
;; restrictions: interleave

(ns p39.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn interleave-seq
  [xs1 xs2]
  (apply concat (map #(vector %1 %2) xs1 xs2)))

(deftest tests
  (testing "test1"
    (is (= (interleave-seq [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))))
  (testing "test2"
    (is (= (interleave-seq [1 2] [3 4 5 6]) '(1 3 2 4))))
  (testing "test3"
    (is (= (interleave-seq [1 2 3 4] [5]) [1 5]))))
