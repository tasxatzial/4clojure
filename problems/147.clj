;; p147: Pascal's Trapezoid

;; Write a function that, for any given input vector of numbers, returns an infinite
;; lazy sequence of vectors, where each next one is constructed from the previous following
;; the rules used in Pascal's Triangle. For example, for [3 1 2], the next row is [3 4 3 2].
;; Beware of arithmetic overflow! In clojure (since version 1.3 in 2011), if you use an
;; arithmetic operator like + and the result is too large to fit into a 64-bit integer, an
;; exception is thrown. You can use +' to indicate that you would rather overflow into
;; Clojure's slower, arbitrary-precision bigint.

(ns p147.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn next-row
  "Given a row, it returns the next one following the rules used in
  Pascal's Triangle."
  [row]
  (let [zero-append-row (conj row 0)
        zero-prepend-row (into [0] row)]
    (mapv +' zero-append-row zero-prepend-row)))

(defn pascal-trapezoid
  [xs]
  (lazy-seq
    (cons xs (pascal-trapezoid (next-row xs)))))

(deftest tests
  (testing "test1"
    (is (= (second (pascal-trapezoid [2 3 2])) [2 5 5 2])))
  (testing "test2"
    (is (= (take 5 (pascal-trapezoid [1])) [[1] [1 1] [1 2 1] [1 3 3 1] [1 4 6 4 1]])))
  (testing "test3"
    (is (= (take 2 (pascal-trapezoid [3 1 2])) [[3 1 2] [3 4 3 2]])))
  (testing "test4"
    (is (= (take 100 (pascal-trapezoid [2 4 2])) (rest (take 101 (pascal-trapezoid [2 2])))))))
