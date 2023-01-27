;; p67: Prime Numbers

;; Write a function which returns the first x number of prime numbers.

(ns p67.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn composite?
  "Returns true if N has any of the primes as a factor, else false."
  [N primes]
  (->> primes
       (take-while #(< % (inc (Math/sqrt N))))
       (some #(zero? (mod N %)))))

(defn primes-lazy
  "Returns a lazy seq of primes."
  []
  (letfn [(_primes [result N]
            (lazy-seq
              (if (composite? N result)
                (_primes result (inc N))
                (cons N (_primes (conj result N) (inc N))))))]
    (_primes [] 2)))

(defn primes
  [x]
  (loop [x x
         result []
         N 2]
    (if (zero? x)
      result
      (if (composite? N result)
        (recur x result (inc N))
        (recur (dec x) (conj result N) (inc N))))))

(defn primes2
  [x]
  (take x (primes-lazy)))

(deftest tests-primes
  (testing "test1"
    (is (= (primes 2) [2 3])))
  (testing "test2"
    (is (= (primes 5) [2 3 5 7 11])))
  (testing "test3"
    (is (= (last (primes 100)) 541))))

(deftest tests-primes2
  (testing "test1"
    (is (= (primes2 2) [2 3])))
  (testing "test2"
    (is (= (primes2 5) [2 3 5 7 11])))
  (testing "test3"
    (is (= (last (primes2 100)) 541))))
