;; p171: Intervals

;; Write a function that takes a sequence of integers and returns a sequence of
;; "intervals". Each interval is a a vector of two integers, start and end, such
;; that all integers between start and end (inclusive) are contained in the input
;; sequence.

(ns p171.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn intervals
  [coll]
  (if (empty? coll)
    []
    (let [sorted-and-distinct (sort (distinct coll))]
      (loop [coll (rest sorted-and-distinct)
             result []
             interval-start (first sorted-and-distinct)
             interval-end interval-start]
        (if (empty? coll)
          (conj result [interval-start interval-end])
          (let [[curr-el & rest-coll] coll]
            (if (= (inc interval-end) curr-el)
              (recur rest-coll result interval-start curr-el)
              (let [updated-result (conj result [interval-start interval-end])]
                (recur rest-coll updated-result curr-el curr-el)))))))))

(deftest tests-create-map2
  (testing "test1"
    (is (= (intervals [1 2 3]) [[1 3]])))
  (testing "test2"
    (is (= (intervals [10 9 8 1 2 3]) [[1 3] [8 10]])))
  (testing "test3"
    (is (= (intervals [1 1 1 1 1 1 1]) [[1 1]])))
  (testing "test4"
    (is (= (intervals []) [])))
  (testing "test5"
    (is (= (intervals [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11])
           [[1 4] [6 6] [9 11] [13 17] [19 19]]))))
