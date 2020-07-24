;p41: Drop Every Nth Item
;Write a function which drops every Nth item from a sequence
(def p41 (fn [col N]
           ((fn my-dropNth [result idx]
              (if (= idx (count col))
                result
                (if (= 0 (mod (+ idx 1) N))
                  (recur result (+ idx 1))
                  (recur (concat result (list (nth col idx))) (+ idx 1)))))
            '() 0)))

;tests
(p41 [1 2 3 4 5 6 7 8] 3)
(p41 [:a :b :c :d :e :f] 2)
(p41 [1 2 3 4 5 6] 4)