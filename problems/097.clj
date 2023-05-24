;; p97: Pascal's Triangle

;; Pascal's triangle is a triangle of numbers computed using the following rules:
;; - The first row is 1.
;; - Each successive row is computed by adding together adjacent numbers in the row
;;   above, and adding a 1 to the beginning and end of the row.
;; Write a function which returns the nth row of Pascal's Triangle

(ns p97.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn next-row
  "Returns the next row of Pascal's triangle."
  [row]
  (let [zero-append (conj row 0)]
    (mapv +' zero-append (rseq zero-append))))

(defn row
  "Returns the Nth row of Pascal's triangle."
  [N]
  (loop [row [1]
         N N]
    (if (= N 1)
      row
      (recur (next-row row) (dec N)))))

(deftest tests
  (testing "test1"
    (is (= (row 1) [1])))
  (testing "test2"
    (is (= (map row (range 1 6))
           [[1]
            [1 1]
            [1 2 1]
            [1 3 3 1]
            [1 4 6 4 1]])))
  (testing "test3"
    (is (= (row 11)
           [1 10 45 120 210 252 210 120 45 10 1]))))
