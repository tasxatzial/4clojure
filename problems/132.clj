;p132: Insert between two items
;Write a function that takes a two-argument predicate, a value, and a collection; and returns a new collection
;where the value is inserted between every two items that satisfy the predicate
(def p132 (fn [pred val col]
            (if (empty? col)
              col
              ((fn my-ins [idx]
                 (if (nil? (nth col (+ idx 1) nil))
                   [(nth col idx)]
                   (if (pred (nth col idx) (nth col (+ idx 1)))
                     (lazy-seq (cons (nth col idx) (cons val (my-ins (+ idx 1)))))
                     (lazy-seq (cons (nth col idx) (my-ins (+ idx 1)))))))
               0))))

;tests
(p132 < :less [1 6 7 4 3])
(p132 > :more [2])
(p132 #(and (pos? %) (< % %2)) :x (range 5))
(empty? (p132 > :more ()))
(take 12 (->> [0 1]
              (iterate (fn [[a b]] [b (+ a b)]))
              (map first) ; fibonacci numbers
              (p132 (fn [a b] ; both even or both odd
                      (= (mod a 2) (mod b 2)))
                    :same)))