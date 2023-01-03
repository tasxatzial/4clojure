;; p80: Perfect Numbers

;; A number is "perfect" if the sum of its divisors equal the number itself.
;; 6 is a perfect number because 1+2+3=6.
;; Write a function which returns true for perfect numbers and false otherwise.

(ns p80.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn divides?
  [dividend divisor]
  (= 0 (mod dividend divisor)))

(defn perfect?
  ([N]
   (perfect? N 1 0))
  ([N I sum]
   (if (> I (/ N 2))
     (= sum N)
     (if (divides? N I)
       (recur N (inc I) (+ sum I))
       (recur N (inc I) sum)))))

(deftest tests
  (testing "test1"
    (is (= (perfect? 6) true)))
  (testing "test2"
    (is (= (perfect? 7) false)))
  (testing "test3"
    (is (= (perfect? 496) true)))
  (testing "test4"
    (is (= (perfect? 500) false)))
  (testing "test5"
    (is (= (perfect? 8128) true))))
