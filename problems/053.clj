;; p53: Longest Increasing Sub-Seq

;; Given a vector of integers, find the longest consecutive sub-sequence of increasing numbers.
;; If two sub-sequences have the same length, use the one that occurs first.
;; An increasing sub-sequence must have a length of 2 or greater to qualify.

(ns p53.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-non-decreasing-length
  "Returns the length of the first non-decreasing subseq.
  Assumes xs is never empty."
  [xs]
  (loop [[x & rest-xs] xs
         length 1]
    (if (and (seq rest-xs) (> (first rest-xs) x))
      (recur rest-xs (inc length))
      length)))

(defn longest-increasing-subseq
  [xs]
  (loop [_xs xs
         idx 0
         max-length 0
         max-idx 0]
    (if (empty? _xs)
      (if (< max-length 2)
        ()
        (take max-length (drop max-idx xs)))
      (let [non-increasing-length (get-non-decreasing-length _xs)
            new-xs- (drop non-increasing-length _xs)
            new-idx (+ idx non-increasing-length)]
        (if (> non-increasing-length max-length)
          (recur new-xs- new-idx non-increasing-length idx)
          (recur new-xs- new-idx max-length max-idx))))))

(deftest tests
  (testing "test1"
    (is (= (longest-increasing-subseq [1 0 1 2 3 0 4 5]) [0 1 2 3])))
  (testing "test2"
    (is (= (longest-increasing-subseq [5 6 1 3 2 7]) [5 6])))
  (testing "test3"
    (is (= (longest-increasing-subseq [2 3 3 4 5]) [3 4 5])))
  (testing "test4"
    (is (= (longest-increasing-subseq [7 6 5 4]) []))))
