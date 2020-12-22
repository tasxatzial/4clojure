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
