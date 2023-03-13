;; p100: Least Common Multiple

;; Write a function which calculates the least common multiple. Your function
;; should accept a variable number of positive integers or ratios.

(ns p100.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn GCD
  "Returns the GCD of x,y."
  [x y]
  (if (= 0 y)
    x
    (GCD y (mod x y))))

(defn LCM
  [& args]
  (reduce (fn [result x]
            (/ (* result x) (GCD result x)))
          (first args)
          args))

(deftest tests-LCM
  (testing "test1"
    (is (== (LCM 2 3) 6)))
  (testing "test2"
    (is (== (LCM 5 3 7) 105)))
  (testing "test3"
    (is (== (LCM 1/3 2/5) 2)))
  (testing "test4"
    (is (== (LCM 3/4 1/6) 3/2)))
  (testing "test4"
    (is (== (LCM 7 5/7 2 3/5) 210))))
