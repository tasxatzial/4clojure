;; p67: Prime Numbers

;; Write a function which returns the first x number of prime numbers

(ns p67.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn my-primes
  ([x]
   (my-primes x [] 2))
  ([x result N]
   (if (zero? x)
     result
     (if (some #(zero? (mod N %)) result)
       (recur x result (inc N))
       (recur (dec x) (conj result N) (inc N))))))

(deftest tests
  (testing "test1"
    (is (= (my-primes 2) [2 3])))
  (testing "test2"
    (is (= (my-primes 5) [2 3 5 7 11])))
  (testing "test3"
    (is (= (last (my-primes 100)) 541))))
