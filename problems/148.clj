;; p148: The Big Divide

;; Write a function which calculates the sum of all natural numbers under n
;; (first argument) which are evenly divisible by at least one of a and b
;; (second and third argument). Numbers a and b are guaranteed to be coprimes.
;; Note: Some test cases have a very large n, so the most obvious solution
;; will exceed the time limit

;; Personal note:
;; All numbers < N which are divisible by X form a arithmetic progression.
;; Their sum is: X * q * (q + 1) / 2
;;
;; where q = total numbers < N that divide X
;; and q = (quotient (N-1) X)

(ns p148.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn sum-of-divisible
  "Calculates the sum of the numbers < N which are divisible by X."
  [N X]
  (let [divisible-count (quot (dec N) X)]
    (/ (*' X divisible-count (inc divisible-count)) 2)))

(defn big-divide
  [N a b]
  (let [f (partial sum-of-divisible N)]
    (- (+ (f a) (f b)) (f (* a b)))))

(deftest tests
  (testing "test1"
    (is (= 0 (big-divide 3 17 11))))
  (testing "test2"
    (is (= 23 (big-divide 10 3 5))))
  (testing "test3"
    (is (= 233168 (big-divide 1000 3 5))))
  (testing "test4"
    (is (= "2333333316666668" (str (big-divide 100000000 3 5)))))
  (testing "test5"
    (is (= "110389610389889610389610"
           (str (big-divide (* 10000 10000 10000) 7 11)))))
  (testing "test6"
    (is (= "1277732511922987429116"
           (str (big-divide (* 10000 10000 10000) 757 809)))))
  (testing "test7"
    (is (= "4530161696788274281"
           (str (big-divide (* 10000 10000 1000) 1597 3571))))))
