;; p19: Last element

;; Write a function which returns the last element in a sequence.
;; restrictions: last

(ns p19.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-last
  [xs]
  (when (seq xs)
    (nth xs (dec (count xs)))))

(deftest tests
  (testing "test1"
    (is (= (get-last [1 2 3 4 5]) 5)))
  (testing "test2"
    (is (= (get-last '(5 4 3)) 3)))
  (testing "test3"
    (is (= (get-last ["b" "c" "d"]) "d"))))
