;p90: Cartesian Product
;Write a function which calculates the Cartesian product of two sets
;
(defn insert-into
  "Returns a set in which every element x of col is the vector [x el]."
  [col el]
  (reduce (fn [result x]
            (conj result [el x]))
          #{}
          col))

(defn cartesian-prod
  "Returns the cartesian product of set1, set2."
  [set1 set2]
  (reduce (fn [result x]
            (into result (insert-into set2 x)))
          #{}
          set1))

(= (cartesian-prod #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
   #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
     ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
     ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})
(= (cartesian-prod #{1 2 3} #{4 5})
   #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})
(= 300 (count (cartesian-prod (into #{} (range 10))
                  (into #{} (range 30)))))
