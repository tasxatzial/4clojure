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

(defn LCM1
  [& args]
  (let [max-arg (apply max args)]
    (loop [result max-arg]
      (if (some #(not= 0 (mod result %)) args)
        (recur (+ max-arg result))
        result))))

(defn LCM2
  [& args]
  (reduce (fn [result x]
            (/ (* result x) (GCD result x)))
          (first args)
          args))

(deftest tests-LCM1
  (testing "test1"
    (is (== (LCM1 2 3) 6)))
  (testing "test2"
    (is (== (LCM1 5 3 7) 105)))
  (testing "test3"
    (is (== (LCM1 1/3 2/5) 2)))
  (testing "test4"
    (is (== (LCM1 3/4 1/6) 3/2)))
  (testing "test4"
    (is (== (LCM1 7 5/7 2 3/5) 210))))

(deftest tests-LCM2
  (testing "test1"
    (is (== (LCM2 2 3) 6)))
  (testing "test2"
    (is (== (LCM2 5 3 7) 105)))
  (testing "test3"
    (is (== (LCM2 1/3 2/5) 2)))
  (testing "test4"
    (is (== (LCM2 3/4 1/6) 3/2)))
  (testing "test4"
    (is (== (LCM2 7 5/7 2 3/5) 210))))
