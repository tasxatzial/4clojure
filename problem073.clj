;p73: Analyze a Tic-Tac-Toe Board
;A tic-tac-toe board is represented by a two dimensional vector. X is represented by :x, O is represented by :o,
;and empty is represented by :e. A player wins by placing three Xs or three Os in a horizontal, vertical, or diagonal
;row. Write a function which analyzes a tic-tac-toe board and returns :x if X has won, :o if O has won, and nil
;if neither player has won
(def p73_1 (fn [row]                                        ;analyze a row or column
             (let [sr (set row)]
               (when (= 1 (count sr))
                 (if (contains? sr :x)
                   :x
                   (when (contains? sr :o)
                     :o))))))

(def tic_cols ((fn [tic]                                    ;all columns
                 (apply map vector tic))
               tic))

(def d1 ((fn [tic]                                          ;diagonal 1
           [(get (get tic 0) 0) (get (get tic 1) 1) (get (get tic 2) 2)])
         tic))

(def d2 ((fn [tic]                                          ;diagonal 2
           [(get (get tic 0) 0) (get (get tic 1) 1) (get (get tic 2) 2)])
         tic))

(def p73_2 (fn [tic]                                        ;rows + columns + diagonal 1 + diagonal 2
           (concat tic tic_cols [d1] [d2])))

(def p73_3 (fn [col]                                        ;analyze (rows + columns + diagonal 1 + diagonal 2)
            (set (reduce (fn [result x]                     ;should contain 8 values
                           (concat result [(p73_1 x)]))
                         [] (p73_2 col)))))

(def p73_S (fn [col]                                        ;check for :x :o
             (let [res ((memoize p73_3) col)]
               (if (contains? res :x)
                 :x
                 (if (contains? res :o)
                   :o
                   nil)))))

;tests
(= nil (p73_S [[:e :e :e]
            [:e :e :e]
            [:e :e :e]]))
(= :x (p73_S [[:x :e :o]
           [:x :e :e]
           [:x :e :o]]))
(= :o (p73_S [[:e :x :e]
           [:o :o :o]
           [:x :e :x]]))
(= nil (p73_S [[:x :e :o]
            [:x :x :e]
            [:o :x :o]]))
(= :x (p73_S [[:x :e :e]
           [:o :x :e]
           [:o :e :x]]))
(= :o (p73_S [[:x :e :o]
           [:x :o :e]
           [:o :e :x]]))
(= nil (p73_S [[:x :o :x]
            [:x :o :x]
            [:o :x :o]]))