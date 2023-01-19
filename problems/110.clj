;; p110: Sequence of pronunciations

;; Write a function that returns a lazy sequence of "pronunciations" of a sequence
;; of numbers. A pronunciation of each element in the sequence consists of the number
;; of repeating identical numbers and the number itself. For example, [1 1] is pronounced
;; as [2 1] ("two ones"), which in turn is pronounced as [1 2 1 1] ("one two, one one").
;; Your function should accept an initial sequence of numbers, and return an infinite lazy
;; sequence of pronunciations, each element being a pronunciation of the previous element.

(ns p110.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn pronounce
  "Returns the pronunciation of a sequence of numbers."
  [col]
  (->> col
       (partition-by identity)
       (reduce #(conj %1 (count %2) (first %2)) [])))

(defn pronunciations
  [col]
  (lazy-seq
    (cons (pronounce col) (pronunciations (pronounce col)))))

(deftest tests
  (testing "test1"
    (is (= [[1 1] [2 1] [1 2 1 1]] (take 3 (pronunciations [1])))))
  (testing "test2"
    (is (= [3 1 2 4] (first (pronunciations [1 1 1 4 4])))))
  (testing "test3"
    (is (= [1 1 1 3 2 1 3 2 1 1] (nth (pronunciations [1]) 6))))
  (testing "test4"
    (is (= 338 (count (nth (pronunciations [3 2]) 15))))))
