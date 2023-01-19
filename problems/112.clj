;; p112: Sequs Horribilis

;; Create a function which takes an integer and a nested collection of integers as
;; arguments. Analyze the elements of the input collection and return a sequence which
;; maintains the nested structure, and which includes all elements starting from the
;; head whose sum is less than or equal to the input integer.

(ns p112.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn sequs-horribilis
  [N col]
  ((fn _sequs-horribilis
     [col sum result]
     (if (empty? col)
       result
       (let [current-element (first col)]
         (if (sequential? current-element)
           (let [sublist (_sequs-horribilis current-element sum '())
                 new-sum (+ sum (apply + (flatten sublist)))
                 new-result (concat result [sublist])]
             (recur (next col) new-sum new-result))
           (if (< N (+ sum current-element))
             result
             (let [new-sum (+ sum current-element)
                   new-result (concat result [current-element])]
               (recur (next col) new-sum new-result)))))))
   col 0 '()))

(deftest tests
  (testing "test1"
    (is (=  (sequs-horribilis 10 [1 2 [3 [4 5] 6] 7])
            '(1 2 (3 (4))))))
  (testing "test2"
    (is (=  (sequs-horribilis 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])
            '(1 2 (3 (4 (5 (6 (7)))))))))
  (testing "test3"
    (is (=  (sequs-horribilis 9 (range))
            '(0 1 2 3))))
  (testing "test4"
    (is (=  (sequs-horribilis 1 [[[[[1]]]]])
            '(((((1))))))))
  (testing "test5"
    (is (=  (sequs-horribilis 0 [1 2 [3 [4 5] 6] 7])
            '())))
  (testing "test5"
    (is (=  (sequs-horribilis 0 [0 0 [0 [0]]])
            '(0 0 (0 (0))))))
  (testing "test7"
    (is (=  (sequs-horribilis 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])
            '(-10 (1 (2 3 (4))))))))
