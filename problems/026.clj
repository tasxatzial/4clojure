;; p26: Fibonacci Sequence

;; Write a function which returns the first X fibonacci numbers

(ns p26.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn fib
  ([]
   (fib 1 1))
  ([prev next]
   (lazy-seq (cons prev (fib next (+ prev next))))))

(defn get-fib
  [N]
  (take N (fib)))

(deftest tests
  (testing "test1"
    (is (= (get-fib 3) '(1 1 2))))
  (testing "test2"
    (is (= (get-fib 6) '(1 1 2 3 5 8))))
  (testing "test3"
    (is (= (get-fib 8) '(1 1 2 3 5 8 13 21)))))
