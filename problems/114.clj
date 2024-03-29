;; p114: Global take-while

;; take-while is great for filtering sequences, but it is limited: you can only examine
;; a single item of the sequence at a time. What if you need to keep track of some
;; state as you go over the sequence? Write a function which accepts an integer n,
;; a predicate p, and a sequence. It should return a lazy sequence of items in the list
;; up to, but not including, the nth item that satisfies the predicate.

(ns p114.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn global-take-while
  [n pred xs]
  (letfn [(step [xs result state]
            (lazy-seq
              (when (seq xs)
                (let [x (first xs)]
                  (if (pred x)
                    (if (= (dec n) state)
                      result
                      (cons x (step (rest xs) result (inc state))))
                    (cons x (step (rest xs) result state)))))))]
    (step xs [] 0)))

(deftest tests
  (testing "test1"
    (is (= [2 3 5 7 11 13]
           (global-take-while 4 #(= 2 (mod % 3))
                              [2 3 5 7 11 13 17 19 23]))))
  (testing "test2"
    (is (= ["this" "is" "a" "sentence"]
           (global-take-while 3 #(some #{\i} %)
                              ["this" "is" "a" "sentence" "i" "wrote"]))))
  (testing "test3"
    (is (= ["this" "is"]
           (global-take-while 1 #{"a"}
                              ["this" "is" "a" "sentence" "i" "wrote"])))))
