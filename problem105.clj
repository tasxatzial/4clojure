;p105: Identify keys and values
;Given an input sequence of keywords and numbers, create a map such that each key in the map is a keyword,
;and the value is a sequence of all the numbers (if any) between it and the next keyword in the sequence
(def p105 (fn idk
            ([col] (idk {} col))
            ([result col] (idk result col (first col)))
            ([result col x]
             (if (empty? col)
               result
               (if (keyword? (first col))
                 (recur (assoc result (first col) []) (rest col) (first col))
                 (recur (assoc result x (conj (result x) (first col))) (rest col) x))))))

;tests
(p105 [])
(p105 [:a 1])
(p105 [:a 1, :b 2])
(p105 [:a 1 2 3 :b :c 4])