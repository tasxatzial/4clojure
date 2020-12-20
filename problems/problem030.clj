;p30: Compress a Sequence
;Write a function which removes consecutive duplicates from a sequence

;solution 1 ----------------------------------------
(def p30 (fn my-compress
           ([col] (my-compress col 0 [] (count col)))
           ([col N result col_count]
            (if (= 0 N)
              (recur col (+ N 1) (conj result (first col)) col_count)
              (if (= col_count N)
                result
                (let [nth_col (nth col N)]
                  (if (= nth_col (nth col (- N 1)))
                    (recur col (+ N 1) result col_count)
                    (recur col (+ N 1) (conj result nth_col) col_count))))))))

;solution 1 tests
(let [result (map p30 ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])

;solution 2 ----------------------------------------
(def p30_2 (fn my-compress
             ([col] (my-compress (map identity col) []))
             ([col result]
              (if (= 0 (count col))
                result
                (if (= 1 (count col))
                  (conj result (first col))
                  (if (= (first col) (second col))
                    (recur (rest col) result)
                    (recur (rest col) (conj result (first col)))))))))

;solution 2 tests
(let [result (map p30_2 ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])

;solution 3 ----------------------------------------
(def p30_3 (fn my-compress [col]
             (reduce (fn [result x]
                       (if (or (empty? result) (not (= (last result) x)))
                         (concat result [x])
                         result))
                     '() col)))

;solution 3 tests
(let [result (map p30_3 ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])

;solution 4 ----------------------------------------
(def p30_4 (fn [col]
             (map first (partition-by identity col))))

;solution 4 tests
(let [result (map p30_4 ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])