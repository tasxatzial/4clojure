;; p137: Digits and bases

;; Write a function which returns a sequence of digits of a non-negative number
;; (first argument) in numerical system with an arbitrary base (second argument).
;; Digits should be represented with their integer values, e.g. 15 would be [1 5]
;; in base 10, [1 1 1 1] in base 2 and [15] in base 16.

(ns p137.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn from-base10
  [n base]
  (loop [result ()
         n n]
    (if (zero? n)
      (or (seq result) '(0))
      (let [r (rem n base)
            q (quot n base)]
        (recur (conj result r) q)))))

(deftest tests
  (testing "test1"
    (is (= [1 2 3 4 5 0 1] (from-base10 1234501 10))))
  (testing "test2"
    (is (= [0] (from-base10 0 11))))
  (testing "test3"
    (is (= [1 0 0 1] (from-base10 9 2))))
  (testing "test4"
    (is (= [1 0] (let [n (rand-int 100000)] (from-base10 n n)))))
  (testing "test5"
    (is (= [16 18 5 24 15 1] (from-base10 Integer/MAX_VALUE 42)))))
