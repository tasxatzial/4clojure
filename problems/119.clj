;; p119: Win at Tic-Tac-Toe

;; As in Problem 73, a tic-tac-toe board is represented by a two dimensional vector.
;; X is represented by :x, O is represented by :o, and empty is represented by :e.
;; Create a function that accepts a game piece and board as arguments, and returns a
;; set (possibly empty) of all valid board placements of the game piece which would
;; result in an immediate win.

(ns p119.core
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

(defn add-choice
  "Updates board with choice inserted into the given position.
  Returns nil if the position is already occupied."
  [choice board pos]
  (when (= :e (get-in board pos))
    (assoc-in board pos choice)))

(defn get-board-positions
  "Returns a sequence of the positions of the board."
  []
  (for [x [0 1 2]
        y [0 1 2]]
    [x y]))

(defn get-win-positions
  [choice board]
  (->> (get-board-positions)
       (filter #(winner? (add-choice choice board %)))
       set))

(deftest tests
  (testing "test1"
    (is (= (get-win-positions :x [[:o :e :e]
                                  [:o :x :o]
                                  [:x :x :e]])
           #{[2 2] [0 1] [0 2]})))
  (testing "test2"
    (is (= (get-win-positions :x [[:x :o :o]
                                  [:x :x :e]
                                  [:e :o :e]])
           #{[2 2] [1 2] [2 0]})))
  (testing "test3"
    (is (= (get-win-positions :x [[:x :e :x]
                                  [:o :x :o]
                                  [:e :o :e]])
           #{[2 2] [0 1] [2 0]})))
  (testing "test4"
    (is (= (get-win-positions :x [[:x :x :o]
                                  [:e :e :e]
                                  [:e :e :e]])
           #{})))
  (testing "test5"
    (is (= (get-win-positions :o [[:x :x :o]
                                  [:o :e :o]
                                  [:x :e :e]])
           #{[2 2] [1 1]}))))
