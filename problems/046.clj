;; p46: Flipping out

;; Write a higher-order function which flips the order of the arguments of an input function

(ns p46.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn flip-args
  [f]
  (fn [arg1 arg2]
    (f arg2 arg1)))

(deftest tests
  (testing "test1"
    (is (= 3 ((flip-args nth) 2 [1 2 3 4 5]))))
  (testing "test2"
    (is (= true ((flip-args >) 7 8))))
  (testing "test3"
    (is (= 4 ((flip-args quot) 2 8))))
  (testing "test4"
    (is (= [1 2 3] ((flip-args take) [1 2 3 4 5] 3)))))
