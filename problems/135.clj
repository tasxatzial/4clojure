;; p135: Infix Calculator

;; Your friend Joe is always whining about Lisps using the prefix notation for math. Show him
;; how you could easily write a function that does math using the infix notation. Is your
;; favorite language that flexible, Joe? Write a function that accepts a variable length
;; mathematical expression consisting of numbers and the operations +, -, *, and /. Assume a
;; simple calculator that does not do precedence and instead just calculates left to right.

(ns p135.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn infix-calc
  [& args]
  (loop [result (first args)
         args (rest args)]
    (let [[f n & rest-args] args]
      (if (nil? f)
        result
        (recur (f result n) rest-args)))))

(deftest tests
  (testing "test1"
    (is (= 7  (infix-calc 2 + 5))))
  (testing "test2"
    (is (= 42 (infix-calc 38 + 48 - 2 / 2))))
  (testing "test3"
    (is (= 8  (infix-calc 10 / 2 - 1 * 2))))
  (testing "test4"
    (is (= 72 (infix-calc 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9)))))
