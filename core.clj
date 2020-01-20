(ns four-clojure.core)

;p19: last element
;Write a function which returns the last element in a sequence
;restrictions: last
(def p19 #(if (empty? %)
            nil
            (nth % (- (count %) 1))))
(map p19 [[1 2 3 4 5] '(5 4 3) ["b" "c" "d"]])


;p20: Penultimate element
;Write a function which returns the second to last element from a sequence
(def p20 #(if (>= (count %) 2)
            (nth % (- (count %) 2))
            nil))
(map p20 [(list 1 2 3 4 5) ["a" "b" "c"] [[1 2] [3 4]]])


;p21: nth element
;Write a function which returns the Nth element from a sequence
;restrictions: nth
(def p21 (fn my-nth [col n]
           (if (= n 0)
             (first col)
             (recur (rest col) (- n 1)))))
(map p21 ['(4 5 6 7) [:a :b :c] [1 2 3 4] '([1 2] [3 4] [5 6])] [2 0 1 2])


;p22: Count a Sequence
;Write a function which returns the total number of elements in a sequence
;restrictions: count
(def p22 (fn [col]
           (reduce (fn [new-val x]
                     (inc new-val))
                   0 col)))
(map p22 ['(1 2 3 3 1) "Hello World" [[1 2] [3 4] [5 6]] '(13) '(:a :b :c)])


;p23: Reverse a Sequence
;Write a function which reverses a sequence
;restrictions: reverse, rseq
(def p23 (fn my-reverse [col]
           (reduce (fn [result x]
                     (conj result x))
                   '() col)))
(map p23 [[1 2 3 4 5] (sorted-set 5 7 2 7) [[1 2][3 4][5 6]]])


;p24: Sum It All Up
;Write a function which returns the sum of a sequence of numbers
(def p24 (fn [x]
           (apply + x)))
(map p24 [[1 2 3] (list 0 -2 5 5) #{4 2 1} '(0 0 -1) '(1 10 3)])


;p25: Find the odd numbers
;Write a function which returns only the odd numbers from a sequence
(def p25 (fn [col]
           (filter #(= 1 (mod % 2)) col)))
(map p25 [#{1 2 3 4 5} [4 2 1 6] [2 2 4 6] [1 1 1 3]])


;p26: Fibonacci Sequence
;Write a function which returns the first X fibonacci numbers
(def p26 (fn [n]
           (take n ((fn fib
                      ([] (fib 1 1))
                      ([x1 x2]
                       (let [sum (+ x1 x2)]
                         (cons x1 (lazy-seq (fib x2 sum))))))))))
(map p26 [3 6 8])


;p27: Palindrome Detector
;Write a function which returns true if the given sequence is a palindrome
(def p27 (fn palindrome?
           ([col] (palindrome? col 0))
           ([col n]
            (if (= (count col) n)
              true
              (if (= (nth col (- (count col) 1)) (first col))
                (recur col (+ n 1))
                false)))))
(map p27 ['(1 2 3 4 5) "racecar" [:foo :bar :foo] '(1 1 3 3 1 1) '(:a :b :c)])


;p28: Flatten a Sequence
;Write a function which flattens a sequence
;restrictions: flatten
(def p28 (fn my-flatten [col]
           (reduce (fn [result x]
                     (concat result
                             (if (sequential? x)
                               (my-flatten x)
                               (list x))))
                   '() col)))
(map p28 ['((1 2) 3 [4 [5 6]]) ["a" ["b"] "c"] '((((:a))))])


;p29: Get the Caps
;Write a function which takes a string and returns a new string containing only the capital letters
;solution 1
(def p29 (fn [col]
           (apply str
                  (filter #(and (re-find #"[A-Z]" %) (= (clojure.string/upper-case %) %))
                          (clojure.string/split col, #"")))))
(map p29 ["HeLlO, WoRlD!" "nothing" "$#A(*&987Zf"])

;solution 2: recursion with re-find
(def p29_2 (fn [col]
             (apply str
                    (let [matcher (re-matcher #"[A-Z]" col)]
                      ((fn find-all [has-next result]
                         (if has-next
                           (let [match (re-find matcher)]
                             (recur match (concat result [has-next])))
                           result))
                       (re-find matcher) '())))))
(map p29_2 ["HeLlO, WoRlD!" "nothing" "$#A(*&987Zf"])

;solution 3: re-seq
(def p29_3 (fn [col]
             (apply str
                    (re-seq #"[A-Z]" col))))
(map p29_3 ["HeLlO, WoRlD!" "nothing" "$#A(*&987Zf"])


;p30: Compress a Sequence
;Write a function which removes consecutive duplicates from a sequence
;solution 1: recursion with nth
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
(let [result (map p30 ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])

;solution 2: recursion with rest
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
(let [result (map p30_2 ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])

;solution 3: reduce
(def p30_3 (fn my-compress [col]
             (reduce (fn [result x]
                       (if (or (empty? result) (not (= (last result) x)))
                         (concat result [x])
                         result))
                     '() col)))
(let [result (map p30_3 ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])

;solution 4
(def p30_4 (fn [col]
             (map first (partition-by identity col))))
(let [result (map p30_4 ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])


;p31: Pack a Sequence
;Write a function which packs consecutive duplicates into sub-lists
;solution 1: recursion with nth
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
(map p31 [[1 1 2 1 1 1 3 3] [:a :a :b :b :c] [[1 2] [1 2] [3 4]]])

;solution 2: recursion with rest
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
(map p31_2 [[1 1 2 1 1 1 3 3] [:a :a :b :b :c] [[1 2] [1 2] [3 4]]])

;solution 3
(def p31_3 (fn [col]
             (partition-by identity col)))
(map p31_3 [[1 1 2 1 1 1 3 3] [:a :a :b :b :c] [[1 2] [1 2] [3 4]]])


;p32: Duplicate a Sequence
;Write a function which duplicates each element of a sequence
(def p32 (fn [col]
           (reduce (fn [result x]
                     (concat result [x x]))
                   '() col)))
(map p32 [[1 2 3] [:a :a :b :b] [[1 2] [3 4]]])


;p33: Replicate a Sequence
;Write a function which replicates each element of a sequence a variable number of times
(def p33 (fn [col N]
           (reduce (fn [result x]
                     (concat result (take N (repeat x))))
                   '() col)))
(p33 [1 2 3] 2)
(p33 [:a :b] 4)
(p33 [4 5 6] 1)
(p33 [[1 2] [3 4]] 2)
(p33 [44 33] 2)


;p34: Implement range
;Write a function which creates a list of all integers in a given range
;restrictions: range
(def p34 (fn my-range
           ([N1 N2] (my-range N1 N2 '()))
           ([N1 N2 result]
            (if (= N1 N2)
              result
              (cons N1 (my-range (+ N1 1) N2))))))
(p34 1 4)
(p34 -2 2)
(p34 5 8)


;p38: Maximum value
;Write a function which takes a variable number of parameters and returns the maximum value
;restrictions: max, max-key
;solution 1: recursion with rest
(def p38 (fn [& col]
           (if (empty? col)
             nil
             ((fn my-max [x col]
                (if (empty? col)
                  x
                  (if (> x (first col))
                    (recur x (rest col))
                    (recur (first col) (rest col)))))
              (first col) (rest col)))))
(map #(apply p38 %) [[1 8 3 4] [30 20] [45 67 11]])

;solution2: reduce
(def p38_2 (fn [& col]
             (reduce (fn [max_ x]
                       (if (> x max_)
                         x
                         max_))
                     (first col) (rest col))))
(map #(apply p38_2 %) [[1 8 3 4] [30 20] [45 67 11]])


;p39: Interleave Two Seqs
;Write a function which takes two sequences and returns the first item from each, then the second item from each, then the third, etc
;restrictions: interleave
(def p39 (fn [col1 col2]
           (reduce concat (map (fn [x1 x2]
                                 [x1 x2]) col1 col2))))
(p39 [1 2 3] [:a :b :c])
(p39 [1 2] [3 4 5 6])
(p39 [1 2 3 4] [5])
(p39 [30 20] [25 15])


;p40: Interpose a Seq
;Write a function which separates the items of a sequence by an arbitrary value
;restrictions: interpose
(def p40 (fn [N col]
           (butlast (reduce (fn [result x]
                              (concat result (concat (list x) (list N))))
                            '() col))))
(p40 0 [1 2 3])
(apply str (p40 ", " ["one" "two" "three"]))
(p40 :z [:a :b :c :d])


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
(p41 [1 2 3 4 5 6 7 8] 3)
(p41 [:a :b :c :d :e :f] 2)
(p41 [1 2 3 4 5 6] 4)


;p42: Factorial Fun
;Write a function which calculates factorials
;solution 1: recur
(def p42 (fn [N]
           ((fn my-factorial [N result]
              (if (= N 0)
                result
                (recur (- N 1) (* result N))))
            N 1)))
(map p42 [1 3 5 8])


;p43: Reverse Interleave
;Write a function which reverses the interleave process into x number of subsequences
;solution 1: partition + interleave + recursion
(def p43 (fn [col N]
           (let [T (/ (count col) N)]
             ((fn my-rinterleave [result col]
                (if (empty? col)
                  result
                  (recur (concat result (list (take T col))) (nthrest col T))))
              '() (apply interleave (partition N col))))
           ))
(p43 [1 2 3 4 5 6] 2)
(p43 (range 9) 3)
(p43 (range 10) 5)

;solution 2: partition + recursion
(def p43_2 (fn [col N]
             (let [col_new (partition N col) count_col (count (first col_new))]
               ((fn my-rinteleave [result C]
                  (if (= count_col C)
                    result
                    (recur (concat result (list (map #(nth % C) col_new)) ) (+ C 1))))
                '() 0))))
(p43_2 [1 2 3 4 5 6] 2)
(p43_2 (range 9) 3)
(p43_2 (range 10) 5)
