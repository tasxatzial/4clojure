;; p30: Compress a Sequence

;; Write a function which removes consecutive duplicates from a sequence

(ns p30.core
  (:require [clojure.test :refer [deftest testing is]]))

;; Note: A trivial solution is the built-in function 'dedupe'
;; but it is not listed as a problem restriction

(defn compress-seq
  ([xs]
   (if (empty? xs)
     []
     (compress-seq xs [])))
  ([xs result]
   (let [[x & rest-xs] xs]
     (if (seq rest-xs)
       (if (= x (first rest-xs))
         (recur rest-xs result)
         (recur rest-xs (conj result x)))
       (conj result x)))))

;; lazy
(defn compress-seq2
  [xs]
  (map first (partition-by identity xs)))

;; lazy
(defn compress-seq3
  [xs]
  (lazy-seq
    (when (seq xs)
      (let [[x & rest-xs] xs]
        (if (seq rest-xs)
          (if (= x (first rest-xs))
            (compress-seq3 rest-xs)
            (cons x (compress-seq3 rest-xs)))
          (list x))))))

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

(deftest tests-compress-seq3
  (testing "test1"
    (is (= (apply str (compress-seq3 "Leeeeeerrroyyy")) "Leroy")))
  (testing "test2"
    (is (= (compress-seq3 [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))))
  (testing "test3"
    (is (= (compress-seq3 [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))))
