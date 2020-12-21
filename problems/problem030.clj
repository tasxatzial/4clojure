;p30: Compress a Sequence
;Write a function which removes consecutive duplicates from a sequence

;solution 1: using recursion/nth and checking if nth element is the same as the nth-1 one
(defn my-compress1
  "Removes consecutive duplicates from a sequence."
  ([col] (my-compress1 col 0 [] (count col)))
  ([col N result col_count]
   (if (= 0 N)
     (recur col (+ N 1) (conj result (first col)) col_count)
     (if (= col_count N)
       result
       (let [nth_col (nth col N)]
         (if (= nth_col (nth col (- N 1)))
           (recur col (+ N 1) result col_count)
           (recur col (+ N 1) (conj result nth_col) col_count)))))))

;solution 2: using recursion/rest and checking if the second element is the same as the first one
(defn my-compress2
  "Removes consecutive duplicates from a sequence."
  ([col] (my-compress2 (map identity col) []))
  ([col result]
   (if (= 0 (count col))
     result
     (if (= 1 (count col))
       (conj result (first col))
       (if (= (first col) (second col))
         (recur (rest col) result)
         (recur (rest col) (conj result (first col))))))))

;solution 3: using reduce
(defn my-compress3
  "Removes consecutive duplicates from a sequence."
  [col]
  (reduce (fn [result x]
            (if (or (empty? result) (not= (last result) x))
              (concat result [x])
              result))
          '()
          col))

;solution 4: partition-by
(defn my-compress4
  "Removes consecutive duplicates from a sequence."
  [col]
  (map first (partition-by identity col)))
