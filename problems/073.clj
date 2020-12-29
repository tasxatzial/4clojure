;p73: Analyze a Tic-Tac-Toe Board
;A tic-tac-toe board is represented by a two dimensional vector.
;X is represented by :x, O is represented by :o, and empty is represented by :e.
;A player wins by placing three Xs or three Os in a horizontal, vertical, or diagonal row.
;Write a function which analyzes a tic-tac-toe board
;and returns :x if X has won, :o if O has won, and nil if neither player has won
;
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
  [tic-tac]
  [(get-in tic-tac [0 0])
   (get-in tic-tac [1 1])
   (get-in tic-tac [2 2])])

(defn get-diagonal2
  "Returns the second diagonal of the board."
  [tic-tac]
  [(get-in tic-tac [0 2])
   (get-in tic-tac [1 1])
   (get-in tic-tac [2 0])])

(defn get-col-i
  "Returns the i-th column of the board."
  [tic-tac i]
  [(get-in tic-tac [0 i])
   (get-in tic-tac [1 i])
   (get-in tic-tac [2 i])])

(defn new-tic-tac
  "Returns a new board that includes both diagonals and all 3 columns."
  [tic-tac]
  (let [diag1 (get-diagonal1 tic-tac)
        diag2 (get-diagonal2 tic-tac)
        col1 (get-col-i tic-tac 0)
        col2 (get-col-i tic-tac 1)
        col3 (get-col-i tic-tac 2)
        new-tic-tac (conj tic-tac diag1 diag2 col1 col2 col3)]
    new-tic-tac))

(defn analyze-board
  "Analyzes a tic-tac-toe board and returns :x if x wins, :o if o wins
  and nil otherwise."
  [tic-tac]
  (let [new-tic-tac (new-tic-tac tic-tac)
        analyzed-tic-tac (map analyze new-tic-tac)
        tic-tac-values (set analyzed-tic-tac)]
    (if (contains? tic-tac-values :x)
      :x
      (when (contains? tic-tac-values :o)
        :o))))

;tests
(= nil (analyze-board [[:e :e :e]
                       [:e :e :e]
                       [:e :e :e]]))
(= :x (analyze-board [[:x :e :o]
                      [:x :e :e]
                      [:x :e :o]]))
(= :o (analyze-board [[:e :x :e]
                      [:o :o :o]
                      [:x :e :x]]))
(= nil (analyze-board [[:x :e :o]
                       [:x :x :e]
                       [:o :x :o]]))
(= :x (analyze-board [[:x :e :e]
                      [:o :x :e]
                      [:o :e :x]]))
(= :o (analyze-board [[:x :e :o]
                      [:x :o :e]
                      [:o :e :x]]))
(= nil (analyze-board [[:x :o :x]
                       [:x :o :x]
                       [:o :x :o]]))
