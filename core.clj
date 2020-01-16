(ns four-clojure.core)

;p19: last element
;Write a function which returns the last element in a sequence
;restrictions: last
(map #(if (empty? %)
        nil
        (nth % (- (count %) 1)))
     [[1 2 3 4 5] '(5 4 3) ["b" "c" "d"]])


;p20: Penultimate element
;Write a function which returns the second to last element from a sequence
(map #(if (>= (count %) 2)
        (nth % (- (count %) 2))
        nil)
     [(list 1 2 3 4 5) ["a" "b" "c"] [[1 2] [3 4]]])


;p21: nth element
;Write a function which returns the Nth element from a sequence
;restrictions: nth
(map (fn my-nth [col n]
   (if (= n 0)
     (first col)
     (recur (rest col) (- n 1))))
 ['(4 5 6 7) [:a :b :c] [1 2 3 4] '([1 2] [3 4] [5 6])] [2 0 1 2])


;p22: Count a Sequence
;Write a function which returns the total number of elements in a sequence
;restrictions: count
(map (fn [col]
   (reduce (fn [new-val x]
             (inc new-val))
           0 col))
 ['(1 2 3 3 1) "Hello World" [[1 2] [3 4] [5 6]] '(13) '(:a :b :c)])


;p23: Reverse a Sequence
;Write a function which reverses a sequence
;restrictions: reverse, rseq
(map (fn my-reverse
   [col]
   (reduce (fn [result x]
             (conj result x))
           '() col))
 [[1 2 3 4 5] (sorted-set 5 7 2 7) [[1 2][3 4][5 6]]])