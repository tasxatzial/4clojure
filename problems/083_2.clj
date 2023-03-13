;; p83: A Half-Truth

;; Write a function which takes a variable number of booleans. Your function should
;; return true if some of the parameters are true, but not all of the parameters are
;; true. Otherwise your function should return false.

(ns p83.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn half-truth
  [& args]
  (let [true-args (filter true? args)]
    (and (boolean (seq true-args))
         (not= (count args) (count true-args)))))

(deftest tests
  (testing "test1"
    (is (= false (half-truth false false))))
  (testing "test2"
    (is (= true (half-truth true false))))
  (testing "test3"
    (is (= false (half-truth true))))
  (testing "test4"
    (is (= true (half-truth false true false))))
  (testing "test5"
    (is (= false (half-truth true true true))))
  (testing "test6"
    (is (= true (half-truth true true true false)))))
