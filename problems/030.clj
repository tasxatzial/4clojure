;; p30: Compress a Sequence

;; Write a function which removes consecutive duplicates from a sequence

(ns p30.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn compress-seq
  ([xs]
   (if (empty? xs)
     []
     (compress-seq (map identity xs) [])))
  ([[x & rest-xs] result]
   (if (seq rest-xs)
     (if (= x (first rest-xs))
       (recur rest-xs result)
       (recur rest-xs (conj result x)))
     (conj result x))))

(defn compress-seq2
  [xs]
  (map first (partition-by identity xs)))

(deftest tests-compress-seq
  (testing "test1"
    (is (= (apply str (compress-seq "Leeeeeerrroyyy")) "Leroy")))
  (testing "test2"
    (is (= (compress-seq [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))))
  (testing "test3"
    (is (= (compress-seq [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))))

(deftest tests-compress-seq2
  (testing "test1"
    (is (= (apply str (compress-seq2 "Leeeeeerrroyyy")) "Leroy")))
  (testing "test2"
    (is (= (compress-seq2 [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))))
  (testing "test3"
    (is (= (compress-seq2 [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))))
