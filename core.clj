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


;p24: Sum It All Up
;Write a function which returns the sum of a sequence of numbers
(map (fn [x]
   (apply + x))
 [[1 2 3] (list 0 -2 5 5) #{4 2 1} '(0 0 -1) '(1 10 3)])


;p25: Find the odd numbers
;Write a function which returns only the odd numbers from a sequence
(map (fn [col]
   (filter #(= 1 (mod % 2)) col))
 [#{1 2 3 4 5} [4 2 1 6] [2 2 4 6] [1 1 1 3]])


;p26: Fibonacci Sequence
;Write a function which returns the first X fibonacci numbers
(map (fn [n]
   (take n ((fn fib
              ([] (fib 1 1))
              ([x1 x2]
               (let [sum (+ x1 x2)]
                 (cons x1 (lazy-seq (fib x2 sum)))))))))
 [3 6 8])


;p27: Palindrome Detector
;Write a function which returns true if the given sequence is a palindrome
(map (fn palindrome?
   ([col] (palindrome? col 0))
   ([col n]
    (if (= (count col) n)
      true
      (if (= (nth col (- (count col) 1)) (first col))
        (recur col (+ n 1))
        false))
    ))
 ['(1 2 3 4 5) "racecar" [:foo :bar :foo] '(1 1 3 3 1 1) '(:a :b :c)])


;p28: Flatten a Sequence
;Write a function which flattens a sequence
;restrictions: flatten
(map (fn my-flatten
   ([col]
    (reduce (fn [result x]
              (concat result
                      (if (sequential? x)
                        (my-flatten x)
                        (list x))))
            '() col)))
 ['((1 2) 3 [4 [5 6]]) ["a" ["b"] "c"] '((((:a))))])


;p29: Get the Caps
;Write a function which takes a string and returns a new string containing only the capital letters
;solution 1
(map (fn [col]
   (apply str
          (filter #(and (re-find #"[A-Z]" %) (= (clojure.string/upper-case %) %))
                  (clojure.string/split col, #""))))
 ["HeLlO, WoRlD!" "nothing" "$#A(*&987Zf"])

;solution 2: recursion with re-find
(map (fn [col]
   (apply str
          (let [matcher (re-matcher #"[A-Z]" col)]
            ((fn find-all [has-next result]
               (if has-next
                 (let [match (re-find matcher)]
                   (recur match (concat result [has-next])))
                 result))
             (re-find matcher) '()))))
 ["HeLlO, WoRlD!" "nothing" "$#A(*&987Zf"])

;solution 3: re-seq
(map (fn [col]
   (apply str
          (re-seq #"[A-Z]" col)))
 ["HeLlO, WoRlD!" "nothing" "$#A(*&987Zf"])


;p30: Compress a Sequence
;Write a function which removes consecutive duplicates from a sequence
;solution 1: recursion with nth
(let [result
      (map (fn my-compress
             ([col] (my-compress col 0 [] (count col)))
             ([col N result col_count]
              (if (= 0 N)
                (recur col (+ N 1) (conj result (first col)) col_count)
                (if (= col_count N)
                  result
                  (let [nth_col (nth col N)]
                    (if (= nth_col (nth col (- N 1)))
                      (recur col (+ N 1) result col_count)
                      (recur col (+ N 1) (conj result nth_col) col_count)))))))
           ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])

;solution 2: recursion with rest
(let [result
      (map (fn my-compress
             ([col] (my-compress (map identity col) []))
             ([col result]
              (if (= 0 (count col))
                result
                (if (= 1 (count col))
                  (conj result (first col))
                  (if (= (first col) (second col))
                    (my-compress (rest col) result)
                    (my-compress (rest col) (conj result (first col))))))))
           ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])

;solution 3: reduce
(let [result
      (map (fn my-compress [col]
             (reduce (fn [result x]
                       (if (empty? result)
                         (concat result [x])
                         (if (not (= (last result) x))
                           (concat result [x])
                           result)))
                     '() col))
           ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])

;solution 4
(let [result
      (map (fn [col]
             (map first (partition-by identity col)))
           ["Leeeeeerrroyyy" [1 1 2 3 3 2 2 3] [[1 2] [1 2] [3 4] [1 2]]])]
  [(apply str (first result)) (second result) (nth result 2)])


;p31: Pack a Sequence
;Write a function which packs consecutive duplicates into sub-lists
(map (fn my-pack
   ([col] (my-pack col 0 [] '()))
   ([col N result result_tmp]
    (if (= N 0)
      (my-pack col (+ N 1) result (concat result_tmp (list (first col))))
      (if (= (count col) N)
        (conj result result_tmp)
        (let [nth_col (nth col N)]
          (if (= nth_col (nth col (- N 1)))
            (my-pack col (+ N 1) result (concat result_tmp (list nth_col)))
            (my-pack col (+ N 1) (conj result result_tmp) (list nth_col))))))))
 [[1 1 2 1 1 1 3 3] [:a :a :b :b :c] [[1 2] [1 2] [3 4]]])