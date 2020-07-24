;p31: Pack a Sequence
;Write a function which packs consecutive duplicates into sub-lists

;solution 1 ----------------------------------------
(def p31 (fn my-pack
           ([col] (my-pack col 0 [] '()))
           ([col N result result_tmp]
            (if (= N 0)
              (recur col (+ N 1) result (concat result_tmp (list (first col))))
              (if (= (count col) N)
                (conj result result_tmp)
                (let [nth_col (nth col N)]
                  (if (= nth_col (nth col (- N 1)))
                    (recur col (+ N 1) result (concat result_tmp (list nth_col)))
                    (recur col (+ N 1) (conj result result_tmp) (list nth_col)))))))))

;solution 1 tests
(map p31 [[1 1 2 1 1 1 3 3] [:a :a :b :b :c] [[1 2] [1 2] [3 4]]])

;solution 2 ----------------------------------------
(def p31_2 (fn my-pack
             ([col] (my-pack col [] '()))
             ([col result result_tmp]
              (if (= 0 (count col))
                result
                (if (= 1 (count col))
                  (conj result (concat result_tmp (list (first col))))
                  (if (= (first col) (second col))
                    (recur (rest col) result (concat result_tmp (list (first col))))
                    (recur (rest col) (conj result (concat result_tmp (list (first col)))) '())))))))

;solution 2 tests
(map p31_2 [[1 1 2 1 1 1 3 3] [:a :a :b :b :c] [[1 2] [1 2] [3 4]]])

;solution 3 ----------------------------------------
(def p31_3 (fn [col]
             (partition-by identity col)))

;solution 2 tests
(map p31_3 [[1 1 2 1 1 1 3 3] [:a :a :b :b :c] [[1 2] [1 2] [3 4]]])