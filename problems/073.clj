;; p73: Analyze a Tic-Tac-Toe Board

;; A tic-tac-toe board is represented by a two dimensional vector.
;; X is represented by :x, O is represented by :o, and empty is represented by :e.
;; A player wins by placing three Xs or three Os in a horizontal, vertical, or diagonal row.
;; Write a function which analyzes a tic-tac-toe board
;; and returns :x if X has won, :o if O has won, and nil if neither player has won.

(ns p73.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn winning-player
  "Returns :x or :o if all elements are :x or :o respectively.
  Returns nil otherwise."
  [line]
  (case line
    [:x :x :x] :x
    [:o :o :o] :o
    nil))

(defn get-diagonal1
  [board]
  (mapv (partial get-in board) [[0 0] [1 1] [2 2]]))

(defn get-diagonal2
  [board]
  (mapv (partial get-in board) [[0 2] [1 1] [2 0]]))

(defn get-col
  [i board]
  (mapv (partial get-in board) [[0 i] [1 i] [2 i]]))

(defn get-row
  [i board]
  (get board i))

(defn get-lines
  [board]
  [(get-diagonal1 board)
   (get-diagonal2 board)
   (get-col 0 board)
   (get-col 1 board)
   (get-col 2 board)
   (get-row 0 board)
   (get-row 1 board)
   (get-row 2 board)])

(defn winner?
  [board]
  (some #(winning-player %) (get-lines board)))

(deftest tests
  (testing "test1"
    (is (= nil (winner? [[:e :e :e]
                               [:e :e :e]
                               [:e :e :e]]))))
  (testing "test2"
    (is (= :x (winner? [[:x :e :o]
                              [:x :e :e]
                              [:x :e :o]]))))
  (testing "test3"
    (is (= :o (winner? [[:e :x :e]
                              [:o :o :o]
                              [:x :e :x]]))))
  (testing "test4"
    (is (= nil (winner? [[:x :e :o]
                               [:x :x :e]
                               [:o :x :o]]))))
  (testing "test5"
    (is (= :x (winner? [[:x :e :e]
                              [:o :x :e]
                              [:o :e :x]]))))
  (testing "test6"
    (is (= :o (winner? [[:x :e :o]
                              [:x :o :e]
                              [:o :e :x]]))))
  (testing "test7"
    (is (= nil (winner? [[:x :o :x]
                               [:x :o :x]
                               [:o :x :o]])))))
