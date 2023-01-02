;; p73: Analyze a Tic-Tac-Toe Board

;; A tic-tac-toe board is represented by a two dimensional vector.
;; X is represented by :x, O is represented by :o, and empty is represented by :e.
;; A player wins by placing three Xs or three Os in a horizontal, vertical, or diagonal row.
;; Write a function which analyzes a tic-tac-toe board
;; and returns :x if X has won, :o if O has won, and nil if neither player has won.

(ns p73.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn analyze-line
  "Analyzes a line (row, column, diagonal). Returns :x or :o if all elements
  are :x or :o respectively. Returns nil otherwise."
  [line]
  (cond
    (= line [:x :x :x]) :x
    (= line [:o :o :o]) :o
    :else nil))

(defn get-diagonal1
  "Returns the first diagonal of the board."
  [board]
  [(get-in board [0 0]) (get-in board [1 1]) (get-in board [2 2])])

(defn get-diagonal2
  "Returns the second diagonal of the board."
  [board]
  [(get-in board [0 2]) (get-in board [1 1]) (get-in board [2 0])])

(defn get-col
  "Returns the i-th column of the board."
  [i board]
  [(get-in board [0 i]) (get-in board [1 i]) (get-in board [2 i])])

(defn get-row
  "Returns the i-th row of the board."
  [i board]
  (get board i))

;; Vector with functions that return all board rows, columns, diagonals
(def get-lines-fns
  [get-diagonal1 get-diagonal2
   (partial get-col 0) (partial get-col 1) (partial get-col 2)
   (partial get-row 0) (partial get-row 1) (partial get-row 2)])

(defn analyze-board
  [board]
  (loop [[get-line & rest-get-lines] get-lines-fns]
    (when get-line
      (or (analyze-line (get-line board))
          (recur rest-get-lines)))))

(deftest tests
  (testing "test1"
    (is (= nil (analyze-board [[:e :e :e]
                               [:e :e :e]
                               [:e :e :e]]))))
  (testing "test2"
    (is (= :x (analyze-board [[:x :e :o]
                              [:x :e :e]
                              [:x :e :o]]))))
  (testing "test3"
    (is (= :o (analyze-board [[:e :x :e]
                              [:o :o :o]
                              [:x :e :x]]))))
  (testing "test4"
    (is (= nil (analyze-board [[:x :e :o]
                               [:x :x :e]
                               [:o :x :o]]))))
  (testing "test5"
    (is (= :x (analyze-board [[:x :e :e]
                              [:o :x :e]
                              [:o :e :x]]))))
  (testing "test6"
    (is (= :o (analyze-board [[:x :e :o]
                              [:x :o :e]
                              [:o :e :x]]))))
  (testing "test7"
    (is (= nil (analyze-board [[:x :o :x]
                               [:x :o :x]
                               [:o :x :o]])))))
