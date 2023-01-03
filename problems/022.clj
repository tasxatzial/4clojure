;; p22: Count a Sequence

;; Write a function which returns the total number of elements in a sequence.
;; restrictions: count

(ns p22.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-count
  [xs]
  (reduce (fn [res _]
            (inc res))
          0
          xs))

(deftest tests
  (testing "test1"
    (is (= (get-count '(1 2 3 3 1)) 5)))
  (testing "test2"
    (is (= (get-count "Hello World") 11)))
  (testing "test3"
    (is (= (get-count [[1 2] [3 4] [5 6]]) 3)))
  (testing "test4"
    (is (= (get-count '(13)) 1)))
  (testing "test5"
    (is (= (get-count '(:a :b :c)) 3))))
