;p119
;As in Problem 73, a tic-tac-toe board is represented by a two dimensional vector.
;X is represented by :x, O is represented by :o, and empty is represented by :e.
;Create a function that accepts a game piece and board as arguments, and returns a
;set (possibly empty) of all valid board placements of the game piece which would
;result in an immediate win.

(defn analyze
  "Analyzes a row or column. Returns :x or :o if all elements are :x or :o
  respectively. Returns nil otherwise."
  [line]
  (let [set-line (set line)]
    (when (= 1 (count set-line))
      (if (contains? set-line :x)
        :x
        (when (contains? set-line :o)
          :o)))))

(defn get-diagonal1
  "Returns the first diagonal of the board."
  [board]
  [(get-in board [0 0])
   (get-in board [1 1])
   (get-in board [2 2])])

(defn get-diagonal2
  "Returns the second diagonal of the board."
  [board]
  [(get-in board [0 2])
   (get-in board [1 1])
   (get-in board [2 0])])

(defn get-col-i
  "Returns the i-th column of the board."
  [board i]
  [(get-in board [0 i])
   (get-in board [1 i])
   (get-in board [2 i])])

(defn full-board
  "Returns a new board that includes both diagonals and all 3 columns."
  [board]
  (let [diag1 (get-diagonal1 board)
        diag2 (get-diagonal2 board)
        col1 (get-col-i board 0)
        col2 (get-col-i board 1)
        col3 (get-col-i board 2)
        new-board (conj board diag1 diag2 col1 col2 col3)]
    new-board))

(defn board-insert
  "Returns a new board with piece inserted into position [x y]. If position
   [x y] is already occupied, it returns nil."
  [piece [x y] board]
  (if (or (= :x (get-in board [x y]))
          (= :o (get-in board [x y])))
    nil
    (let [row (get board x)
          new-row (assoc row y piece)]
      (assoc board x new-row))))

(defn winning-board?
  "Analyzes a tic-tac-toe board and returns true if it represents
   a winning board."
  [board]
  (when board
    (let [full-board (full-board board)
          analyzed-board (map analyze full-board)
          values (set analyzed-board)]
      (or (contains? values :x)
          (contains? values :o)))))

(defn immediate-win-positions
  "Returns a set of all immediate winning positions when piece is inserted
   into the empty positions of the board."
  [piece board]
  (let [positions (for [x [0 1 2]
                        y [0 1 2]]
                    [x y])]
    (reduce (fn [result [x y]]
              (let [inserted-board (board-insert piece [x y] board)]
                (if (winning-board? inserted-board)
                 (conj result [x y])
                  result)))
            #{}
            positions)))

;tests
(= (immediate-win-positions :x [[:o :e :e]
                                [:o :x :o]
                                [:x :x :e]])
   #{[2 2] [0 1] [0 2]})
(= (immediate-win-positions :x [[:x :o :o]
                                [:x :x :e]
                                [:e :o :e]])
   #{[2 2] [1 2] [2 0]})
(= (immediate-win-positions :x [[:x :e :x]
                                [:o :x :o]
                                [:e :o :e]])
   #{[2 2] [0 1] [2 0]})
(= (immediate-win-positions :x [[:x :x :o]
                                [:e :e :e]
                                [:e :e :e]])
   #{})
(= (immediate-win-positions :o [[:x :x :o]
                                [:o :e :o]
                                [:x :e :e]])
   #{[2 2] [1 1]})
