;; p21: Nth element

;; Write a function which returns the Nth element from a sequence
;; restrictions: nth

(ns p21.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-nth
  [xs n]
  (if (zero? n)
    (first xs)
    (recur (rest xs) (dec n))))

(deftest tests
  (testing "test1"
    (is (= (get-nth '(4 5 6 7) 2) 6)))
  (testing "test2"
    (is (= (get-nth [:a :b :c] 0) :a)))
  (testing "test3"
    (is (= (get-nth [1 2 3 4] 1) 2)))
  (testing "test4"
    (is (= (get-nth '([1 2] [3 4] [5 6]) 2) [5 6]))))
