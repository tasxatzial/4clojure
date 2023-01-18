;; p95: To Tree, or not to Tree

;; Write a predicate which checks whether or not a given sequence represents a binary
;; tree. Each node in the tree must have a value, a left child, and a right child.

(ns p95.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn binary-tree?
  [node]
  (or (nil? node)
      (and (sequential? node) 
           (= (count node) 3)
           (binary-tree? (second node))
           (binary-tree? (last node)))))

(deftest tests
  (testing "test1"
    (is (= (binary-tree? '(:a (:b nil nil) nil))
           true)))
  (testing "test2"
    (is (= (binary-tree? '(:a (:b nil nil)))
           false)))
  (testing "test3"
    (is (= (binary-tree? [1 nil [2 [3 nil nil] [4 nil nil]]])
           true)))
  (testing "test4"
    (is (= (binary-tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]])
           false)))
  (testing "test5"
    (is (= (binary-tree? [1 [2 [3 [4 nil nil] nil] nil] nil])
           true)))
  (testing "test6"
    (is (= (binary-tree? [1 [2 [3 [4 false nil] nil] nil] nil])
           false)))
  (testing "test7"
    (is (= (binary-tree? '(:a nil ()))
           false))))
