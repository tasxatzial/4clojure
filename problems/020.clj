;; p20: Penultimate element

;; Write a function which returns the second to last element from a sequence.

(ns p20.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-second-to-last
  [xs]
  (let [c (count xs)]
    (when (> c 1)
      (nth xs (- c 2)))))

(deftest tests
  (testing "test1"
    (is (= (get-second-to-last (list 1 2 3 4 5)) 4)))
  (testing "test2"
    (is (= (get-second-to-last ["a" "b" "c"]) "b")))
  (testing "test3"
    (is (= (get-second-to-last [[1 2] [3 4]]) [1 2]))))
