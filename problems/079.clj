;; p79: Triangle Minimal Path

;; Write a function which calculates the sum of the minimal path through a triangle.
;; The triangle is represented as a vector of vectors. The path should start at
;; the top of the triangle and move to an adjacent number on the next row until the
;; bottom of the triangle is reached.

(ns p79.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn last-row?
  [triangle row]
  (= (dec (count triangle)) row))

(defn min-sum-from-pos
  [triangle row col]
  (if (last-row? triangle row)
    (get-in triangle [row col])
    (let [x (min-sum-from-pos triangle (inc row) col)
          y (min-sum-from-pos triangle (inc row) (inc col))]
      (+ (min x y) (get-in triangle [row col])))))

(defn min-sum
  [triangle]
  (min-sum-from-pos triangle 0 0))

(deftest tests
  (testing "test1"
    (is (= 7 (min-sum      [   [1]
                              [2 4]
                             [5 1 4]
                            [2 3 4 5]]))))
  (testing "test2"
    (is (= 20 (min-sum      [     [3]
                                 [2 4]
                                [1 9 3]
                               [9 9 2 4]
                              [4 6 6 7 8]
                             [5 7 3 5 1 4]])))))
