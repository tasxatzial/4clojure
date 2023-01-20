;; p137: Digits and bases

;; Write a function which returns a sequence of digits of a non-negative number
;; (first argument) in numerical system with an arbitrary base (second argument).
;; Digits should be represented with their integer values, e.g. 15 would be [1 5]
;; in base 10, [1 1 1 1] in base 2 and [15] in base 16.

(ns p137.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn to-digits
  [n base]
  (if (= 0 n)
    '(0)
    (loop [result ()
           n n]
      (if (= 0 n)
        result
        (recur (conj result (mod n base))
               (quot n base))))))

(deftest tests
  (testing "test1"
    (is (= [1 2 3 4 5 0 1] (to-digits 1234501 10))))
  (testing "test2"
    (is (= [0] (to-digits 0 11))))
  (testing "test3"
    (is (= [1 0 0 1] (to-digits 9 2))))
  (testing "test4"
    (is (= [1 0] (let [n (rand-int 100000)] (to-digits n n)))))
  (testing "test5"
    (is (= [16 18 5 24 15 1] (to-digits Integer/MAX_VALUE 42)))))
