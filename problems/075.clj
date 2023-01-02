;; p75: Euler's Totient Function

;; Two numbers are coprime if their greatest common divisor equals 1. Euler's totient
;; function f(x) is defined as the number of positive integers less than x which are
;; coprime to x. The special case f(1) equals 1.
;; Write a function which calculates Euler's totient function.

(ns p75.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-gcd
  "Returns the GCD of N1, N2."
  [N1 N2]
  (if (= 0 N2)
    N1
    (recur N2 (mod N1 N2))))

(defn get-euler-totient
  [N]
  (if (= 1 N)
    1
    (let [get-N-gcd (partial get-gcd N)
          gcds (map get-N-gcd (range 1 N))]
      (count (filter #{1} gcds)))))

(deftest tests
  (testing "test1"
    (is (= (get-euler-totient 1) 1)))
  (testing "test2"
    (is (= (get-euler-totient 10) (count '(1 3 7 9)) 4)))
  (testing "test3"
    (is (= (get-euler-totient 40) 16)))
  (testing "test4"
    (is (= (get-euler-totient 99) 60))))
