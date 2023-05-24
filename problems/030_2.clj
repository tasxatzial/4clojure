;; p30: Compress a Sequence

;; Write a function which removes consecutive duplicates from a sequence.

;; Note: A trivial solution is the built-in function 'dedupe'
;; but it is not listed as a problem restriction.

(ns p30.core
  (:require [clojure.test :refer [deftest testing is]]))

;; lazy
(defn compress-seq
  [xs]
  (map first (partition-by identity xs)))

(deftest tests
  (testing "test1"
    (is (= (apply str (compress-seq "Leeeeeerrroyyy")) "Leroy")))
  (testing "test2"
    (is (= (compress-seq [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))))
  (testing "test3"
    (is (= (compress-seq [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))))
