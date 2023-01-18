;; p96: Beauty is Symmetry

;; Let us define a binary tree as "symmetric" if the left half of the tree is the mirror
;; image of the right half of the tree. Write a predicate to determine whether or not a
;; given binary tree is symmetric (see p95 for a reminder on the tree representation).

(ns p96.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn symmetric-trees?
  "Returns true the given trees are symmetric."
  [left-node right-node]
  (if (and (sequential? left-node) (sequential? right-node))
    (and (= (first left-node) (first right-node))
         (symmetric-trees? (second left-node) (last right-node))
         (symmetric-trees? (second right-node) (last left-node)))
    (= left-node right-node)))

(defn symmetric-tree?
  [node]
  (or (nil? node)
      (symmetric-trees? (second node) (last node))))

(deftest tests
  (testing "test1"
    (is (= (symmetric-tree? '(:a (:b nil nil) (:b nil nil))) true)))
  (testing "test2"
    (is (= (symmetric-tree? '(:a (:b nil nil) nil)) false)))
  (testing "test3"
    (is (= (symmetric-tree? '(:a (:b nil nil) (:c nil nil))) false)))
  (testing "test4"
    (is (= (symmetric-tree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                             [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
           true)))
  (testing "test5"
    (is (= (symmetric-tree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                             [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
           false)))
  (testing "test6"
    (is (= (symmetric-tree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                             [2 [3 nil [4 [6 nil nil] nil]] nil]])
           false))))
