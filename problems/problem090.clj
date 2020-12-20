;p90: Cartesian Product
;Write a function which calculates the Cartesian product of two sets
(def p90 (fn [set1 set2]
           (reduce (fn [result x]
                     (into result (reduce (fn [result2 x2]
                                            (conj result2 [x x2]))
                                          #{} set2)))
                   #{} set1)))

;tests
(= (p90 #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
   #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
     ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
     ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})
(p90 #{1 2 3} #{4 5})
(= 300 (count (p90 (into #{} (range 10))
                  (into #{} (range 30)))))