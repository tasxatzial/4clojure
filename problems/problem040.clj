;p40: Interpose a Seq
;Write a function which separates the items of a sequence by an arbitrary value
;
;restrictions: interpose
;
(def p40 (fn [N col]
           (butlast (reduce (fn [result x]
                              (concat result (concat (list x) (list N))))
                            '() col))))

;tests
(p40 0 [1 2 3])
(apply str (p40 ", " ["one" "two" "three"]))
(p40 :z [:a :b :c :d])