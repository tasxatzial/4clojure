;; p119: Win at Tic-Tac-Toe

;; As in Problem 73, a tic-tac-toe board is represented by a two dimensional vector.
;; X is represented by :x, O is represented by :o, and empty is represented by :e.
;; Create a function that accepts a game piece and board as arguments, and returns a
;; set (possibly empty) of all valid board placements of the game piece which would
;; result in an immediate win.

(ns p119.core
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
  (mapv (partial get-in board) [[0 0] [1 1] [2 2]]))

(defn get-diagonal2
  "Returns the second diagonal of the board."
  [board]
  (mapv (partial get-in board) [[0 2] [1 1] [2 0]]))

(defn get-col
  "Returns the i-th column of the board."
  [i board]
  (mapv (partial get-in board) [[0 i] [1 i] [2 i]]))

(defn get-row
  "Returns the i-th row of the board."
  [i board]
  (get board i))

;; Vector with functions that return all board rows, columns, diagonals
(def get-lines-fns
  (-> [get-diagonal1 get-diagonal2]
      (into (map #(partial get-col %) [0 1 2]))
      (into (map #(partial get-row %) [0 1 2]))))

(defn winner?
  "Analyzes the board and returns :x or :o based on the winner.
  Returns nil otherwise."
  [board]
  (when board
    (some #(analyze-line (% board)) get-lines-fns)))

(defn board-insert
  "Returns a new board with piece inserted into position [x y].
  Returns nil if the position is already occupied."
  [piece board [x y]]
  (when (= :e (get-in board [x y]))
    (assoc-in board [x y] piece)))

(defn get-board-positions
  "Returns a seq of the positions of the board starting from [0 0]."
  []
  (for [x [0 1 2]
        y [0 1 2]]
    [x y]))

(def memoized_get-board-positions
  (memoize get-board-positions))

(defn immediate-win-positions
  [piece board]
  (let [update-position (partial board-insert piece board)]
    (->> (memoized_get-board-positions)
         (filter #(winner? (update-position %)))
         set)))

(deftest tests
  (testing "test1"
    (is (= (immediate-win-positions :x [[:o :e :e]
                                        [:o :x :o]
                                        [:x :x :e]])
           #{[2 2] [0 1] [0 2]})))
  (testing "test2"
    (is (= (immediate-win-positions :x [[:x :o :o]
                                        [:x :x :e]
                                        [:e :o :e]])
           #{[2 2] [1 2] [2 0]})))
  (testing "test3"
    (is (= (immediate-win-positions :x [[:x :e :x]
                                        [:o :x :o]
                                        [:e :o :e]])
           #{[2 2] [0 1] [2 0]})))
  (testing "test4"
    (is (= (immediate-win-positions :x [[:x :x :o]
                                        [:e :e :e]
                                        [:e :e :e]])
           #{})))
  (testing "test5"
    (is (= (immediate-win-positions :o [[:x :x :o]
                                        [:o :e :o]
                                        [:x :e :e]])
           #{[2 2] [1 1]}))))
