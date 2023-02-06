;; p112: Sequs Horribilis

;; Create a function which takes an integer and a nested collection of integers as
;; arguments. Analyze the elements of the input collection and return a sequence which
;; maintains the nested structure, and which includes all elements starting from the
;; head whose sum is less than or equal to the input integer.

(ns p112.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn sequs-horribilis
  [N coll]
  (letfn [(_sequs-horribilis [coll sum result]
            (if (empty? coll)
              result
              (let [curr-el (first coll)]
                (if (sequential? curr-el)
                  (let [sublist (_sequs-horribilis curr-el sum ())
                        new-sum (+ sum (reduce + (flatten sublist)))
                        new-result (concat result [sublist])]
                    (recur (next coll) new-sum new-result))
                  (if (< N (+ sum curr-el))
                    result
                    (let [new-sum (+ sum curr-el)
                          new-result (concat result [curr-el])]
                      (recur (next coll) new-sum new-result)))))))]
    (_sequs-horribilis coll 0 ())))

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
