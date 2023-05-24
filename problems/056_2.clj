;; p56: Find Distinct Items

;; Write a function which removes the duplicates from a sequence. Order of the items
;; must be maintained.
;; restrictions: distinct

(ns p56.core
  (:require [clojure.test :refer [deftest testing is]]))

;; lazy
(defn remove-duplicates
  [xs]
  (letfn [(step [xs set-xs]
            (lazy-seq
              (when (seq xs)
                (let [[x & rest-xs] xs]
                  (if (contains? set-xs x)
                    (step rest-xs set-xs)
                    (cons x (step rest-xs (conj set-xs x))))))))]
    (step xs #{})))

(deftest tests
  (testing "test1"
    (is (= (remove-duplicates [1 2 1 3 1 2 4]) [1 2 3 4])))
  (testing "test2"
    (is (= (remove-duplicates [:a :a :b :b :c :c]) [:a :b :c])))
  (testing "test3"
    (is (= (remove-duplicates '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))))
  (testing "test4"
    (is (= (remove-duplicates (range 50)) (range 50)))))
