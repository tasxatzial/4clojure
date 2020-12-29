;p31: Pack a Sequence
;Write a function which packs consecutive duplicates into sub-lists

;solution 1: using recursion/nth and checking if the nth element is the same as the nth-1 one
(defn my-pack1
  "Packs consecutive duplicates into sub-lists."
  ([col] (my-pack1 col 0 [] '()))
  ([col N result result_tmp]
   (if (= N 0)
     (recur col (+ N 1) result (concat result_tmp (list (first col))))
     (if (= (count col) N)
       (conj result result_tmp)
       (let [nth_col (nth col N)]
         (if (= nth_col (nth col (- N 1)))
           (recur col (+ N 1) result (concat result_tmp (list nth_col)))
           (recur col (+ N 1) (conj result result_tmp) (list nth_col))))))))

;solution 2: using recursion/rest and checking if the second element is the same as the first one
(defn my-pack2
  "Packs consecutive duplicates into sub-lists."
  ([col] (my-pack2 col [] '()))
  ([col result result_tmp]
   (if (= 0 (count col))
     result
     (if (= 1 (count col))
       (conj result (concat result_tmp (list (first col))))
       (if (= (first col) (second col))
         (recur (rest col) result (concat result_tmp (list (first col))))
         (recur (rest col) (conj result (concat result_tmp (list (first col)))) '()))))))

;solution 3: partition-by
(defn my-pack3
  "Packs consecutive duplicates into sub-lists."
  [col]
  (partition-by identity col))

;tests
(= (my-pack1 [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (my-pack1 [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (my-pack1 [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))

(= (my-pack2 [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (my-pack2 [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (my-pack2 [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))
