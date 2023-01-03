;; p60: Sequence Reductions

;; Write a function which behaves like reduce, but returns each intermediate
;; value of the reduction. Your function must accept either two or three arguments,
;; and the return sequence must be lazy.
;; restrictions: reductions

(ns p60.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn seq-reductions
  ([f xs]
   (lazy-seq
     (when (seq xs)
       (let [[x & rest-xs] xs]
         (cons (f x) (seq-reductions (partial f x) rest-xs))))))
  ([f init xs]
   (lazy-seq
     (cons init (seq-reductions (partial f init) xs)))))

(deftest tests
  (testing "test1"
    (is (= (take 5 (seq-reductions + (range))) [0 1 3 6 10])))
  (testing "test2"
    (is (= (seq-reductions conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])))
  (testing "test3"
    (is (= (last (seq-reductions * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120))))
