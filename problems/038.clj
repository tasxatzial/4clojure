;; p38: Maximum value

;; Write a function which takes a variable number of parameters and returns the maximum value.
;; restrictions: max, max-key

(ns p38.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-max
  [& xs]
  (loop [[x & rest-xs] (rest xs)
         result (first xs)]
    (if x
      (if (> x result)
        (recur rest-xs x)
        (recur rest-xs result))
      result)))

(deftest tests
  (testing "test1"
    (is (= (get-max 1 8 3 4) 8)))
  (testing "test2"
    (is (= (get-max 30 20) 30)))
  (testing "test3"
    (is (= (get-max 45 67 11) 67))))
