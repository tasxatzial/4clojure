;; p67: Prime Numbers

;; Write a function which returns the first x number of prime numbers.

(ns p67.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn prime?
  "Returns true if N is prime. This will happen if N is not a multiple
  of any number in the given list of primes."
  [N primes]
  (reduce (fn [result prime]
            (if (> prime (inc (Math/sqrt N)))
              (reduced true)
              (if (= 0 (mod N prime))
                (reduced false)
                result)))
          true
          primes))

(defn primes
  "Returns the first x primes."
  [x]
  (loop [x x
         result []
         N 2]
    (if (zero? x)
      result
      (if (prime? N result)
        (recur (dec x) (conj result N) (inc N))
        (recur x result (inc N))))))

(deftest tests-primes
  (testing "test1"
    (is (= (primes 2) [2 3])))
  (testing "test2"
    (is (= (primes 5) [2 3 5 7 11])))
  (testing "test3"
    (is (= (last (primes 100)) 541))))
