;; p28: Flatten a Sequence

;; Write a function which flattens a sequence.
;; restrictions: flatten

(ns p28.core
  (:require [clojure.test :refer [deftest testing is]]))

;; lazy
(defn make-flat
  [xs]
  (lazy-seq
    (when (seq xs)
      (let [[x & rest-xs] xs]
        (if (sequential? x)
          (concat (make-flat x) (make-flat rest-xs))
          (cons x (make-flat rest-xs)))))))

(deftest tests
  (testing "test1"
    (is (= (make-flat '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))))
  (testing "test2"
    (is (= (make-flat ["a" ["b"] "c"]) '("a" "b" "c"))))
  (testing "test3"
    (is (= (make-flat '((((:a))))) '(:a)))))
