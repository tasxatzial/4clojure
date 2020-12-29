;p41: Drop Every Nth Item
;Write a function which drops every Nth item from a sequence
;
(defn drop-nth
  "Drops every Nth item from a sequence."
  ([col N] (drop-nth col N 0 []))
  ([col N count result]
   (if (empty? col)
     result
     (let [new-result (if (= 0 (mod (inc count) N))
                        result
                        (conj result (first col)))]
       (recur (rest col) N (inc count) new-result)))))

;tests
(= (drop-nth [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
(= (drop-nth [:a :b :c :d :e :f] 2) [:a :c :e])
(= (drop-nth [1 2 3 4 5 6] 4) [1 2 3 5 6])
