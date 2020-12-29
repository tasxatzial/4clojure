;p132: Insert between two items
;Write a function that takes a two-argument predicate, a value, and a collection; and returns
;a new collection where the value is inserted between every two items that satisfy the predicate
;
(defn insert-between
  "Returns a new collection where the value is inserted between every two
   items that satisfy the predicate."
  [pred val col]
  (if (empty? col)
    col
    ((fn _insert-between [idx]
       (let [next-item (nth col (inc idx) nil)
             cur-item (nth col idx)]
         (if (nil? next-item)
           [cur-item]
           (if (pred cur-item next-item)
             (lazy-seq (cons cur-item (cons val (_insert-between (inc idx)))))
             (lazy-seq (cons cur-item (_insert-between (inc idx))))))))
     0)))

;tests
(= '(1 :less 6 :less 7 4 3) (insert-between < :less [1 6 7 4 3]))
(= '(2) (insert-between > :more [2]))
(= [0 1 :x 2 :x 3 :x 4]  (insert-between #(and (pos? %) (< % %2)) :x (range 5)))
(empty? (insert-between > :more ()))

(= [0 1 :same 1 2 3 :same 5 8 13 :same 21]
   (take 12 (->> [0 1]
                 (iterate (fn [[a b]] [b (+ a b)]))
                 (map first) ; fibonacci numbers
                 (insert-between (fn [a b] ; both even or both odd
                       (= (mod a 2) (mod b 2)))
                     :same))))
