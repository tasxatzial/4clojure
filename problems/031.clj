;; p31: Pack a Sequence

;; Write a function which packs consecutive duplicates into sub-lists.

(ns p31.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn partition-by-identity
  [xs]
  (loop [[x & rest-xs] xs
         equal-count 1
         result []]
    (if (seq rest-xs)
      (if (= x (first rest-xs))
        (recur rest-xs (inc equal-count) result)
        (recur rest-xs 1 (conj result (repeat equal-count x))))
      (conj result (repeat equal-count x)))))


(deftest tests
  (testing "test1"
    (is (= (partition-by-identity [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))))
  (testing "test2"
    (is (= (partition-by-identity [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))))
  (testing "test3"
    (is (= (partition-by-identity [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4]))))))
