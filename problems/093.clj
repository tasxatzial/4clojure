;; p93: Partially Flatten a Sequence

;; Write a function which flattens any nested combination of sequential things
;; (lists, vectors, etc.), but maintains the lowest level sequential items.
;; The result should be a sequence of sequences with only one level of nesting.

(ns p93.core
  (:require [clojure.test :refer [deftest testing is]]))

;; lazy
(defn make-flat
  [xs]
  (lazy-seq
    (when (seq xs)
      (let [[x & rest-xs] xs]
        (if (sequential? x)
          (if (every? (complement sequential?) x)
            (cons x (make-flat rest-xs))
            (concat (make-flat x) (make-flat rest-xs)))
          (cons x (make-flat rest-xs)))))))

(deftest tests
  (testing "test1"
    (is (= (make-flat [["Do"] ["Nothing"]])
           [["Do"] ["Nothing"]])))
  (testing "test2"
    (is (= (make-flat [[[[:a :b]]] [[:c :d]] [:e :f]])
           [[:a :b] [:c :d] [:e :f]])))
  (testing "test3"
    (is (= (make-flat '((1 2) ((3 4) ((((5 6)))))))
           '((1 2) (3 4) (5 6))))))
