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
;works due to the fact that (cons X nil) -> (X)
(def p34 (fn my-range [N1 N2]
           (when (< N1 N2)
             (cons N1 (my-range (+ N1 1) N2)))))
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
;Write a function which takes two sequences and returns the first item from each, then the second item from each,
;then the third, etc
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

;solution 2: recursion but in non-tail position
(def p42_2 (fn my-recursion [N]
             (if (= N 0)
               1
               (* N (my-recursion (- N 1))))))
(map p42_2 [1 3 5 8])


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
               ((fn my-rinterleave [result C]
                  (if (= count_col C)
                    result
                    (recur (concat result (list (map #(nth % C) col_new)) ) (+ C 1))))
                '() 0))))
(p43_2 [1 2 3 4 5 6] 2)
(p43_2 (range 9) 3)
(p43_2 (range 10) 5)

;solution 3: partition + interleave
(def p43_3 (fn [col N]
             (let [partitioned (partition N col) interleaved (apply interleave partitioned)]
               (partition (count partitioned) interleaved))))
(p43_3 [1 2 3 4 5 6] 2)
(p43_3 (range 9) 3)
(p43_3 (range 10) 5)

;solution 4
(def p43_4 (fn [col N]
             (apply map list (partition N col))))
(p43_4 [1 2 3 4 5 6] 2)
(p43_4 (range 9) 3)
(p43_4 (range 10) 5)


;p44: Rotate Sequence
;Write a function which can rotate a sequence in either direction
(def p44 (fn [N col]
           (let [N (mod N (count col))]
             (concat (drop N col) (take N col)))))
(p44 6 [1 2 3 4 5])
(p44 -2 [1 2 3 4 5])
(p44 6 [1 2 3 4 5])
(p44 1 '(:a :b :c))
(p44 -4 '(:a :b :c))


;p45: Intro to Iterate
;The iterate function can be used to produce an infinite lazy sequence
(take 5 (iterate #(+ 3 %) 1))                               ;(1 4 7 10 13)


;p46: Flipping out
;Write a higher-order function which flips the order of the arguments of an input function
(def p46 (fn [func]
           (fn [arg1 arg2]
             (func arg2 arg1))))
((p46 nth) 2 [1 2 3 4 5])
((p46 >) 7 8)
((p46 quot) 2 8)
((p46 take) [1 2 3 4 5] 3)


;p47: Contain Yourself
;The contains? function checks if a KEY is present in a given collection.
;This often leads beginner clojurians to use it incorrectly with numerically indexed collections like vectors and lists
(contains? #{4 5 6} 4)                                      ;true; 4
(contains? [1 1 1 1 1] 4)                                   ;true, 4th index
(contains? {4 :a 2 :b} 4)                                   ;true, 4
(not (contains? [1 2 4] 4))                                 ;true, 4th index


;p48: Intro to some
;The some function takes a predicate function and a collection.
;It returns the first logical true value of (predicate x) where x is an item in the collection
;restrictions: split-at
(= 6 (some #{2 7 6} [5 6 7 8]))                            ;6
(= 6 (some #(when (even? %) %) [5 6 7 8]))                 ;6


;p49: Split a sequence
;Write a function which will split a sequence into two parts
(def p49 (fn [N col]
           [(apply vector (take N col)) (apply vector (drop N col))]))
(p49 3 [1 2 3 4 5 6])
(p49 1 [:a :b :c :d])
(p49 2 [[1 2] [3 4] [5 6]])


;p50: Split by Type
;Write a function which takes a sequence consisting of items with different types and splits them up into a set of
;homogeneous sub-sequences. The internal order of each sub-sequence should be maintained, but the sub-sequences
;themselves can be returned in any order (this is why 'set' is used in the test cases)
;solution 1: filter
(def p50 (fn [col]
           (map #(apply vector %)
                (filter #(not-empty %)
                        (map #(filter % col) [keyword? number? string? vector?])))))
(set (p50 [1 2 3 :a]))
(set (p50 [:a "foo"  "bar" :b]))
(set (p50 [[1 2] :a [3 4] 5 6 :b]))

;solution 2: group-by
(def p50_2 (fn [col]
             (filter #(not (nil? %))
                     (map #(% true)
                          (map #(group-by % col) [keyword? number? string? vector?])))))
(set (p50_2 [1 2 3 :a]))
(set (p50_2 [:a "foo"  "bar" :b]))
(set (p50_2 [[1 2] :a [3 4] 5 6 :b]))


;p51: Advanced Destructuring
;Here is an example of some more sophisticated destructuring
(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d])) ;[1 2 3 4 5]


;p52: Intro to Destructuring
;Let bindings and function parameter lists support destructuring
(= [2 4] (let [[a b c d e] [0 1 2 3 4]] [c e]))             ;[c e]


;p53: Longest Increasing Sub-Seq
;Given a vector of integers, find the longest consecutive sub-sequence of increasing numbers.
;If two sub-sequences have the same length, use the one that occurs first.
;An increasing sub-sequence must have a length of 2 or greater to qualify
(def p53 (fn [col]
           (reduce (fn [result x]
                     (if (> (count x) (count result))
                       x
                       result))
                   [] (filter #(> (count %) 1)
                              (reduce (fn [result x]
                                        (if (empty? result)
                                          (conj result [x])
                                          (if (> x (last (last result)))
                                            (conj (apply vector (butlast result)) (conj (apply vector (last result)) x))
                                            (conj result [x]))))
                                      [] col)))))
(p53 [1 0 1 2 3 0 4 5])
(p53 [5 6 1 3 2 7])
(p53 [2 3 3 4 5])
(p53 [7 6 5 4])


;p54: Partition a Sequence
;Write a function which returns a sequence of lists of x items each. Lists of less than x items should not be returned
;restrictions: partition, partition-all
;solution 1: lazy-seq
(def p54 (fn my-partition [N col]
           (take (Math/floor (/ (count col) N))  (concat (list (take N col)) (lazy-seq (my-partition N (drop N col)))))))
(p54 3 (range 9))
(p54 2 (range 8))
(p54 3 (range 8))

;solution 2: non-lazy recursion
(def p54_2 (fn [N col]
             ((fn my-partition [result col]
                (if (< (count col) N)
                  result
                  (recur (concat result (list (take N col))) (drop N col))))
              '() col)))
(p54_2 3 (range 9))
(p54_2 2 (range 8))
(p54_2 3 (range 8))

;solution 3: recursion but in non-tail position
;works due to the fact that (cons X nil) -> (X)
(def p54_3 (fn [N col]
             (concat [] ((fn my-partition [col]             ;concat necessary if we pass empty seq
                           (when (>= (count col) N)
                             (cons (take N col) (my-partition (drop N col)))))
                         col))))
(p54_3 3 (range 9))
(p54_3 2 (range 8))
(p54_3 3 (range 8))


;p55: Count Occurrences
;Write a function which returns a map containing the number of occurrences of each distinct item in a sequence
;restrictions: frequencies
(def p55 (fn [col]
            (reduce (fn [result x]
                      (if (result x)
                        (conj result [x (+ 1 (result x))])
                        (conj result [x 1])))
                    {} col)))
(p55 [1 1 2 3 2 1 1])
(p55 [:b :a :b :a :b])
(p55 '([1 2] [1 3] [1 3]))


;p56: Find Distinct Items
;Write a function which removes the duplicates from a sequence. Order of the items must be maintained
;restrictions: distinct
(def p56 (fn [col]
           (reduce (fn [result x]
                     (if ((set result) x)
                       result
                       (conj result x)))
                   [] col)))
(p56 [1 2 1 3 1 2 4])
(p56 [:a :a :b :b :c :c])
(p56 '([2 4] [1 2] [1 3] [1 3]))
(p56 (range 50))


;p57: Simple Recursion
;A recursive function is a function which calls itself.
;This is one of the fundamental techniques used in functional programming
(= [5 4 3 2 1] ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5)) ;[5 4 3 2 1]


;p58: Function Composition
;Write a function which allows you to create function compositions.
;The parameter list should take a variable number of functions, and create a function that applies them from
;right-to-left
;restrictions: comp
;partial solution: compose two functions
(def p58_tmp (fn my-comp [f1 f2]
           (fn [& args]
             (f1 (apply f2 args)))))

;solution: compose any number of functions
(def p58_2 (fn my-comp [& f-args]
             (fn [& args]
               (if (= 1 (count f-args))
                 (apply (first f-args) args)
                 ((first f-args) (apply (apply my-comp (rest f-args)) args))))))
((p58_2 rest reverse) [1 2 3 4])
((p58_2 (partial + 3) second) [1 2 3 4])
((p58_2 zero? #(mod % 8) +) 3 5 7 9)
((p58_2 #(.toUpperCase %) #(apply str %) take) 5 "hello world")


;p59: Juxtaposition
;Take a set of functions and return a new function that takes a variable number of arguments and returns a sequence
;containing the result of applying each function left-to-right to the argument list
;restrictions: juxt
(def p59 (fn [& f-args]
           (fn [& args]
             (map #(apply % args) f-args))))
((p59 + max min) 2 3 5 1 6 4)
((p59 #(.toUpperCase %) count) "hello")
((p59 :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})


;p61: Map Construction
;Write a function which takes a vector of keys and a vector of values and constructs a map from them
(def p61 (fn [col1 col2]
           (into {} (map (fn [x1 x2]
                           [x1 x2])
                         col1 col2))))
(p61 [:a :b :c] [1 2 3])
(p61 [1 2 3 4] ["one" "two" "three"])
(p61 [:foo :bar] ["foo" "bar" "baz"])


;p62: Re-implement Iterate
;Given a side-effect free function f and an initial value x write a function which returns an infinite
;lazy sequence of x, (f x), (f (f x)), (f (f (f x))), etc
;restrictions: iterate
(def p62 (fn my-iterate [func i]
           (cons i (lazy-seq (my-iterate func (func i))))))
(take 5 (p62 #(* 2 %) 1))
(take 100 (p62 inc 0))
(take 9 (p62 #(inc (mod % 3)) 1))


;p63: Group a Sequence
;Given a function f and a sequence s, write a function which returns a map.
;The keys should be the values of f applied to each item in s.
;The value at each key should be a vector of corresponding items in the order they appear in s
;restrictions: group-by
(def p63 (fn [func col]
            (reduce (fn [result x]
                      (if (contains? result (x 1))
                        (into result {(x 1) (conj (result (x 1)) (x 0))} )
                        (into result {(x 1) [(x 0)]})))
                    {} (into [] (map (fn [x]
                                       [x, (func x)])
                                     col)))))
(p63 #(> % 5) [1 3 6 8])
(p63 #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
(p63 count [[1] [1 2] [3] [1 2 3] [2 3]])


;p64: Intro to Reduce
;Reduce takes a 2 argument function and an optional starting value. It then applies the function to the first 2 items
;in the sequence (or the starting value and the first element of the sequence). In the next iteration the function
;will be called on the previous return value and the next item from the sequence, thus reducing the entire collection
;to one value. Don't worry, it's not as complicated as it sounds
(= 15 (reduce + [1 2 3 4 5]))                              ; +
(=  0 (reduce + []))                                       ; +
(=  6 (reduce + 1 [2 3]))                                  ; +


;p66: Greatest Common Divisor
;Given two integers, write a function which returns the greatest common divisor
(def p66 (fn my-gcd [N1 N2]
           (if (= 0 N2)
             N1
             (my-gcd N2 (mod N1 N2)))))
(p66 2 4)
(p66 10 5)
(p66 5 7)
(p66 1023 858)


;p67: Prime Numbers
;Write a function which returns the first x number of prime numbers
(def p67 (fn my-primes
           ([x] (my-primes x [] 2))
           ([x result N]
            (if (= x 0)
              result
              (if (contains? (set (map #(mod N %) result)) 0)
                (my-primes x result (+ N 1))
                (my-primes (- x 1) (conj result N) (+ N 1)))))))
(p67 2)
(p67 5)
(last (p67 100))


;p68: Recurring Theme
;Clojure only has one non-stack-consuming looping construct: recur.
;Either a function or a loop can be used as the recursion point.
;Either way, recur rebinds the bindings of the recursion point to the values it is passed.
;Recur must be called from the tail-position, and calling it elsewhere will result in an error
(= [7 6 5 4 3]                                              ; [7 6 5 4 3]
   (loop [x 5
          result []]
     (if (> x 0)
       (recur (dec x) (conj result (+ 2 x)))
       result)))


;p69: Merge with a Function
;Write a function which takes a function f and a variable number of maps. Your function should return a map that
;consists of the rest of the maps conj-ed onto the first. If a key occurs in more than one map, the mapping(s) from
;the latter (left-to-right) should be combined with the mapping in the result by calling (f val-in-result val-in-latter)
(def p69 (fn [& args]
           (reduce (fn [result x]
                     (conj result (reduce (fn [result2 x2]
                                     (if (contains? result (x2 0))
                                       (into result2 {(x2 0) ((first args) (result (x2 0)) (x2 1))})
                                       (conj result2 x2)))
                                   {} x)))
                   (second args) (nnext args))))
(p69 * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
(p69 - {1 10, 2 20} {1 3, 2 10, 3 15})
(p69 concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})


;p70: Word Sorting
;Write a function that splits a sentence up into a sorted list of words. Capitalization should not affect sort
;order and punctuation should be ignored
(def p70 (fn [string1]
           (sort-by clojure.string/lower-case
                    (clojure.string/split (clojure.string/replace string1 #"[^A-Za-z ]" "") #" "))))
(p70 "Have a nice day.")
(p70 "Clojure is a fun language!")
(p70 "Fools fall for foolish follies.")


;p71: Rearranging Code: ->
;The -> macro threads an expression x through a variable number of forms. First, x is inserted as the second
;item in the first form, making a list of it if it is not a list already. Then the first form is inserted as
;the second item in the second form, making a list of that form if necessary. This process continues for all
;the forms. Using -> can sometimes make your code more readable
(= (last (sort (rest (reverse [2 5 4 1 3 6]))))             ;last
   (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (last))
   5)


;p72: Rearranging Code: ->>
;The ->> macro threads an expression x through a variable number of forms. First, x is inserted as the last
;item in the first form, making a list of it if it is not a list already. Then the first form is inserted as
;the last item in the second form, making a list of that form if necessary. This process continues for all
;the forms. Using ->> can sometimes make your code more readable
(= (apply + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))      ;apply +
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (apply +))
   11)


;Analyze a Tic-Tac-Toe Board
;A tic-tac-toe board is represented by a two dimensional vector. X is represented by :x, O is represented by :o,
;and empty is represented by :e. A player wins by placing three Xs or three Os in a horizontal, vertical, or diagonal
;row. Write a function which analyzes a tic-tac-toe board and returns :x if X has won, :o if O has won, and nil
;if neither player has won
(def p73_1 (fn [row]                                        ;analyze a row or column
             (let [sr (set row)]
               (when (= 1 (count sr))
                 (if (contains? sr :x)
                   :x
                   (when (contains? sr :o)
                     :o))))))

(def tic_cols ((fn [tic]                                    ;all columns
                 (apply map vector tic))
               tic))

(def d1 ((fn [tic]                                          ;diagonal 1
           [(get (get tic 0) 0) (get (get tic 1) 1) (get (get tic 2) 2)])
         tic))

(def d2 ((fn [tic]                                          ;diagonal 2
           [(get (get tic 0) 0) (get (get tic 1) 1) (get (get tic 2) 2)])
         tic))

(def p73_2 (fn [tic]                                        ;rows + columns + diagonal 1 + diagonal 2
           (concat tic tic_cols [d1] [d2])))

(def p73_3 (fn [col]                                        ;analyze (rows + columns + diagonal 1 + diagonal 2)
            (set (reduce (fn [result x]                     ;should contain 8 values
                           (concat result [(p73_1 x)]))
                         [] (p73_2 col)))))

(def p73_S (fn [col]                                        ;check for :x :o
             (let [res ((memoize p73_3) col)]
               (if (contains? res :x)
                 :x
                 (if (contains? res :o)
                   :o
                   nil)))))

;submit to 4clojure
(def p73 (fn [tic]
             (let [res ((memoize (fn [col]
                                   (set (reduce (fn [result x]
                                                  (concat result [((fn [row]
                                                                     (let [sr (set row)]
                                                                       (when (= 1 (count sr))
                                                                         (if (contains? sr :x)
                                                                           :x
                                                                           (when (contains? sr :o)
                                                                             :o))))) x)]))
                                                [] ((fn [tic]
                                                      (concat tic
                                                              ((fn [tic]
                                                                 (apply map vector tic))
                                                               tic)
                                                              [((fn [tic]
                                                                  [(get (get tic 0) 0) (get (get tic 1) 1) (get (get tic 2) 2)])
                                                                tic)]
                                                              [((fn [tic]
                                                                  [(get (get tic 0) 2) (get (get tic 1) 1) (get (get tic 2) 0)])
                                                                tic)]))
                                                    col))))) tic)]
               (if (contains? res :x)
                 :x
                 (if (contains? res :o)
                   :o
                   nil)))))

(= nil (p73 [[:e :e :e]
            [:e :e :e]
            [:e :e :e]]))
(= :x (p73 [[:x :e :o]
           [:x :e :e]
           [:x :e :o]]))
(= :o (p73 [[:e :x :e]
           [:o :o :o]
           [:x :e :x]]))
(= nil (p73 [[:x :e :o]
            [:x :x :e]
            [:o :x :o]]))
(= :x (p73 [[:x :e :e]
           [:o :x :e]
           [:o :e :x]]))
(= :o (p73 [[:x :e :o]
           [:x :o :e]
           [:o :e :x]]))
(= nil (p73 [[:x :o :x]
            [:x :o :x]
            [:o :x :o]]))


;p74: Filter Perfect Squares
;Given a string of comma separated integers, write a function which returns a new comma separated string that only
;contains the numbers which are perfect squares
(def p74 (fn [string]
           (clojure.string/join "," (filter #(< (- (Math/sqrt %) (Math/floor (Math/sqrt %))) 0.001)
                                            (map #(read-string %)
                                                 (clojure.string/split string #","))))))
(p74 "4,5,6,7,8,9")
(p74 "15,16,25,36,37")


;p75: Euler's Totient Function
;Two numbers are coprime if their greatest common divisor equals 1. Euler's totient function f(x) is defined as the
;number of positive integers less than x which are coprime to x. The special case f(1) equals 1. Write a function
;which calculates Euler's totient function
(def p75 (fn [N]
           (if (= 1 N )
             1
             (count (filter #(= 1 %)
                            (map (fn my-gcd [N1 N2]
                                   (if (= 0 N2)
                                     N1
                                     (my-gcd N2 (mod N1 N2)))) (range 1 N) (repeat N)))))))
(p75 1)
(p75 10)
(p75 40)
(p75 99)


;p76: Intro to Trampoline
;The trampoline function takes a function f and a variable number of parameters. Trampoline calls f with any parameters
;that were supplied. If f returns a function, trampoline calls that function with no arguments. This is repeated, until
;the return value is not a function, and then trampoline returns that non-function value. This is useful for
;implementing mutually recursive algorithms in a way that won't consume the stack
(= [1 3 5 7 9 11]
   (letfn
     [(foo [x y] #(bar (conj x y) y))
      (bar [x y] (if (> (last x) 10)
                   x
                   #(foo x (+ 2 y))))]
     (trampoline foo [] 1)))


;p77: Anagram Finder
;Write a function which finds all the anagrams in a vector of words. A word x is an anagram of word y if all the
;letters in x can be rearranged in a different order to form y. Your function should return a set of sets, where each
;sub-set is a group of words which are anagrams of each other. Each sub-set should have at least two words. Words
;without any anagrams should not be included in the result
;solution 1
(def p77 (fn [col]
           (letfn [(p77_3 [col]                             ;collect all strings in every value in a set
                     (reduce (fn [result y]
                               (conj result (reduce (fn [result x]
                                                      (conj result (x 0)))
                                                    #{} y)))
                             #{} col))
                   (p77_2 [col] (filter #(> (count %) 1) col)) ;remove all values that have one element
                   (p77_1 [col] (reduce (fn [result x]      ;get the values in group-by
                                    (conj result (get col x) ))
                                  #{} (keys col)))
                   (p77_0 [col sorted] (group-by #(get % 1) (map (fn [x y] ;group-by the sorted letter list
                                                                   [x y]) col sorted)))
                   (p77_ [col] (map (fn [x]                 ;split into letters, then sort
                                      (sort (clojure.string/split x #""))) col))]
             (p77_3 (p77_2 (p77_1 (p77_0 col (p77_ col))))))))

(p77 ["meat" "mat" "team" "mate" "eat"])
(p77 ["veer" "lake" "item" "kale" "mite" "ever"])

;solution 2
(def p77_2 (fn [col]
             (letfn [(F [string] (sort (clojure.string/split string #"")))
                     (Remove-single [col] (set ( filter #(> (count %) 1) col)))
                     (Process-col [col] (if (empty? col)
                                          #{}
                                          (reduce (fn [result x]
                                                    (if (= (F x) (F (first col)))
                                                      (conj result x)
                                                      result))
                                                  #{(first col)} (next col))))]
               (Remove-single ((fn G [result col]
                                 (if (empty? col)
                                   result
                                   (if (not (some true? (map #(contains? % (first col)) result)))
                                     (G (conj result (Process-col col)) (next col))
                                     (G result (next col)))))
                               #{} col)))))

(p77_2 ["meat" "mat" "team" "mate" "eat"])
(p77_2 ["veer" "lake" "item" "kale" "mite" "ever"])


;p78: Reimplement Trampoline
;Reimplement the function described in "Intro to Trampoline" (problem 76)
;restrictions: trampoline
(def p78 (fn my-trampoline
           ([fn1 & args]
            (if (empty? args)
              (my-trampoline fn1)
              (my-trampoline (apply fn1 args))))
           ([fn1]
            (if (fn? fn1)
              (my-trampoline (fn1))
              fn1))))

(= (letfn [(triple [x] #(sub-two (* 3 x)))
           (sub-two [x] #(stop?(- x 2)))
           (stop? [x] (if (> x 50) x #(triple x)))]
     (p78 triple 2))
   82)
(= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
           (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
     (map (partial p78 my-even?) (range 6)))
   [true false true false true false])


;p79: Triangle Minimal Path
;Write a function which calculates the sum of the minimal path through a triangle. The triangle is represented as a
;collection of vectors. The path should start at the top of the triangle and move to an adjacent number on the next
;row until the bottom of the triangle is reached
(def p79 (fn min-path
           ([col] (min-path col 0 0))
           ([col vidx idx]
            (if (= (- (count col) 1) vidx)
              ((nth col vidx) idx)
              (let [x (min-path col (+ 1 vidx) idx)
                    y (min-path col (+ 1 vidx) (+ 1 idx))]
                (+ (min x y) ((nth col vidx) idx)))))))
(p79 '([1]
       [2 4]
       [5 1 4]
       [2 3 4 5]))
(p79 '([3]
       [2 4]
       [1 9 3]
       [9 9 2 4]
       [4 6 6 7 8]
       [5 7 3 5 1 4]))


;p80: Perfect Numbers
;A number is "perfect" if the sum of its divisors equal the number itself. 6 is a perfect number because 1+2+3=6.
;Write a function which returns true for perfect numbers and false otherwise
(def p80 (fn my-perfect
           ([N] (my-perfect N 1 0))
           ([N I sum]
            (if (> I (/ N 2))
              (= sum N)
              (if (= 0 (mod N I))
                (recur N (+ I 1) (+ sum I))
                (recur N (+ I 1) sum))))))
(p80 6)
(p80 7)
(p80 496)
(p80 500)
(p80 8128)


;p81: Set Intersection
;Write a function which returns the intersection of two sets.
;The intersection is the sub-set of items that each set has in common
;restrictions: intersection
;solution 1: union
(def p81 (fn [col1 col2]
           (reduce (fn [result x]
                     (if (and (contains? col1 x) (contains? col2 x))
                       (conj result x)
                       result))
                   #{} (into col1 col2))))
(p81 #{0 1 2 3} #{2 3 4 5})
(p81 #{0 1 2} #{3 4 5})
(p81 #{:a :b :c :d} #{:c :e :a :f :d})

;solution2
(def p81_2 (fn [col1 col2]
             (reduce (fn [result x]
                       (if (contains? col2 x)
                         (conj result x)
                         result))
                     #{} col1)))
(p81_2 #{0 1 2 3} #{2 3 4 5})
(p81_2 #{0 1 2} #{3 4 5})
(p81_2 #{:a :b :c :d} #{:c :e :a :f :d})


;p83: A Half-Truth
;Write a function which takes a variable number of booleans. Your function should return true if some of the
;parameters are true, but not all of the parameters are true. Otherwise your function should return false
(def p83 (fn [& args]
           (and (or (some true? args) false)  (or (some false? args) false))))
(p83 false false)
(p83 true false)
(p83 true)
(p83 false true false)
(p83 true true true)
(p83 true true true false)


;p85: Power Set
;Write a function which generates the power set of a given set. The power set of a set x is the set of all
;subsets of x, including the empty set and x itself
(def p85 (fn [col]
           (conj (reduce (fn [result x]
                           (into result (conj (reduce (fn [result2 x2]
                                                        (conj result2 (conj x2 x)))
                                                      #{} result)
                                              #{x})))
                         #{} col)
                 #{})))
(p85 #{1 :a})
(p85 #{})
(p85 #{1 2 3})
(= (count (p85 (into #{} (range 10)))) 1024)


;p86: Happy numbers
;Happy numbers are positive integers that follow a particular formula: take each individual digit, square it, and
;then sum the squares to get a new number. Repeat with the new number and eventually, you might get to a number
;whose squared sum is 1. This is a happy number. An unhappy number (or sad number) is one that loops endlessly.
;Write a function that determines if a number is happy or not
(def p86 (fn [N]
           (letfn [(sumd [N]
                     (reduce (fn [result x]
                               (+ result (* x x)))
                             0 (map (comp read-string str) (str N))))]
             ((fn myf [N_ result]
                (if (contains? result N_)
                  false
                  (if (= 1 N_)
                    true
                    (recur (sumd N_) (conj result N_)))))
              (sumd N) #{}))))
(p86 7)
(p86 986543210)
(p86 2)
(p86 3)


;p88: Symmetric Difference
;Write a function which returns the symmetric difference of two sets. The symmetric difference is the set of items
;belonging to one but not both of the two sets
(def p88 (fn [col1 col2]
           (reduce (fn [result x]
                     (if (or (and (contains? col1 x) (not (contains? col2 x)))
                             (and (contains? col2 x) (not (contains? col1 x))))
                       (conj result x)
                       result))
                   #{} (into col1 col2))))
(p88 #{1 2 3 4 5 6} #{1 3 5 7})
(p88 #{:a :b :c} #{})
(p88 #{} #{4 5 6})
(p88 #{[1 2] [2 3]} #{[2 3] [3 4]})

;solution 2
(def p88_2 (fn [col1 col2]
             (reduce (fn [result x]
                       (if (not (contains? (clojure.set/intersection col1 col2) x))
                         (conj result x)
                         result))
                     #{} (into col1 col2))))
(p88_2 #{1 2 3 4 5 6} #{1 3 5 7})
(p88_2 #{:a :b :c} #{})
(p88_2 #{} #{4 5 6})
(p88_2 #{[1 2] [2 3]} #{[2 3] [3 4]})


;p90: Cartesian Product
;Write a function which calculates the Cartesian product of two sets
(def p90 (fn [set1 set2]
           (reduce (fn [result x]
                     (into result (reduce (fn [result2 x2]
                                            (conj result2 [x x2]))
                                          #{} set2)))
                   #{} set1)))
(= (p90 #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
   #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
     ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
     ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})
(p90 #{1 2 3} #{4 5})
(= 300 (count (p90 (into #{} (range 10))
                  (into #{} (range 30)))))


;p93: Partially Flatten a Sequence
;Write a function which flattens any nested combination of sequential things (lists, vectors, etc.), but
;maintains the lowest level sequential items. The result should be a sequence of sequences with only one level of
;nesting
(def p93 (fn my-flatten [col]
           (reduce (fn [result x]
                     (into result
                           (if (sequential? x)
                             (if (sequential? (first x))
                               (my-flatten x)
                               [x])
                             [x])))
                   [] col)))
(p93  [["Do"] ["Nothing"]])
(p93 [[[[:a :b]]] [[:c :d]] [:e :f]])
(p93 '((1 2)((3 4)((((5 6)))))))


;p95: To Tree, or not to Tree
;Write a predicate which checks whether or not a given sequence represents a binary tree. Each node in the tree must
;have a value, a left child, and a right child
(def p95 (fn tree-check [node]
                     (if (nil? node)
                       true
                       (if (sequential? node)
                         (if (and (= (count node) 3)
                                  (tree-check (second node))
                                  (tree-check (nth node 2)))
                           true
                           false)
                         (if node
                           true
                           false)))))
(p95 '(:a (:b nil nil) nil))
(p95 '(:a (:b nil nil)))
(p95 [1 nil [2 [3 nil nil] [4 nil nil]]])
(p95 [1 [2 nil nil] [3 nil nil] [4 nil nil]])
(p95 [1 [2 [3 [4 nil nil] nil] nil] nil])
(p95 [1 [2 [3 [4 false nil] nil] nil] nil])
(p95 '(:a nil ()))


;p96: Beauty is Symmetry
;Let us define a binary tree as "symmetric" if the left half of the tree is the mirror image of the right half of the
;tree. Write a predicate to determine whether or not a given binary tree is symmetric. (see p95: To Tree, or not to Tree
;for a reminder on the tree representation we're using)
(def p96 (fn sym-check [col]
           (if (nil? (first col))
             true
             ((fn sym-check [left-tree right-tree]
                (if (and (sequential? left-tree) (sequential? right-tree))
                  (if (and (sym-check (second left-tree) (nth right-tree 2))
                           (sym-check (second right-tree) (nth left-tree 2)))
                    (= (first left-tree) (first right-tree))
                    false)
                  (= left-tree right-tree)))
              (second col) (nth col 2)))))
(p96 '(:a (:b nil nil) (:b nil nil)))                       ;true
(p96 '(:a (:b nil nil) nil))                                ;false
(p96 '(:a (:b nil nil) (:c nil nil)))                       ;false
(p96 [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]         ;true
      [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
(p96 [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]         ;false
      [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
(p96 [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]         ;false
      [2 [3 nil [4 [6 nil nil] nil]] nil]])


;p97: Pascal's Triangle
;Pascal's triangle is a triangle of numbers computed using the following rules:
;- The first row is 1.
;- Each successive row is computed by adding together adjacent numbers in the row above, and adding a 1 to the
;beginning and end of the row.
;Write a function which returns the nth row of Pascal's Triangle
(def p97 (fn [N]
           (if (= 1 N)
             [1]
             (if (= 2 N)
               [1 1]
               ((fn pascal [N row]
                  (if (= N 0)
                    row
                    (letfn [(new-row [N1 result]
                              (if (= N1 (count row))
                                result
                                (recur (+ N1 1) (conj result (+ (get row (- N1 1)) (get row N1))))))]
                      (recur (- N 1) (into [1] (conj (new-row 1 []) 1))))))
                (- N 2) [1 1])))))
(p97 1)
(map p97 (range 1 6))
(p97 11)


;p98: Equivalence Classes
;A function f defined on a domain D induces an equivalence relation on D, as follows: a is equivalent to b with
;respect to f if and only if (f a) is equal to (f b). Write a function with arguments f and D that computes the
;equivalence classes of D with respect to f
(def p98 (fn [func col]
           (reduce (fn [result x]
                     (conj result (set (x 1))))
                   #{} (group-by func col))))
(p98 #(* % %) #{-2 -1 0 1 2})
(p98 #(rem % 3) #{0 1 2 3 4 5})
(p98 identity #{0 1 2 3 4})
(p98 (constantly true) #{0 1 2 3 4})


;p99: Product Digits
;Write a function which multiplies two numbers and returns the result as a sequence of its digits
(def p99 (fn [x y]
           (map (comp read-string str) (str (* x y)))))
(p99 1 1)
(p99 99 9)
(p99 999 99)


;p100: Least Common Multiple
;Write a function which calculates the least common multiple. Your function should accept a variable number of
;positive integers or ratios
;solution 1: LCM is a multiple of the max number
(def p100 (fn [& args]
            (let [maxN (memoize (fn [args] (apply max args)))]
              ((fn my-lcm[i]
                 (if (some #(not= 0 (mod i %)) args)
                   (recur (+ (maxN args) i))
                   i))
               (maxN args)))))
(p100 2 3)
(p100 3 5 7)
(p100 1/3 2/5)
(p100 3/4 1/6)
(p100 7 5/7 2 3/5)

;solution 2: reduce
(def p100_2 (fn [& args]
              (letfn [(my-gcd [x y]
                        (if (= 0 y)
                          x
                          (my-gcd y (mod x y))))]
                (reduce (fn [result x]
                          (/ (* result x) (my-gcd result x)))
                        (first args) args))))
(p100_2 2 3)
(p100_2 3 5 7)
(p100_2 1/3 2/5)
(p100_2 3/4 1/6)
(p100_2 7 5/7 2 3/5)


;p102: intoCamelCase
;When working with java, you often need to create an object with fieldsLikeThis, but you'd rather work with a hashmap
;that has :keys-like-this until it's time to convert. Write a function which takes lower-case hyphen-separated strings
;and converts them to camel-case strings
(def p102 (fn [s]
            (let [res (memoize (fn [s]
                                 (clojure.string/split s #"-")))]
              (str (first (res s))
                   (apply str (map #(str (clojure.string/upper-case (first %)) (apply str (rest %)))
                                   (rest (res s))))))))
(p102 "something")
(p102 "multi-word-key")
(p102 "leaveMeAlone")


;p103: Generating k-combinations
;Given a sequence S consisting of n elements generate all k-combinations of S, i. e. generate all possible sets
;consisting of k distinct elements taken from S. The number of k-combinations for a sequence is equal to
;the binomial coefficient
;solution 1: using power set
(def p103 (fn [N col]
            (let [res (conj (reduce (fn [result x]
                                      (into result (conj (reduce (fn [result2 x2]
                                                                   (conj result2 (conj x2 x)))
                                                                 #{} result)
                                                         #{x})))
                                    #{} col)
                            #{})]
              (set (filter #(= N (count %)) res)))))
(p103 2 #{4 5 6})
(p103 10 #{4 5 6})
(p103 2 #{0 1 2})
(p103 3 #{0 1 2 3 4})
(p103 4 #{[1 2 3] :a "abc" "efg"})
(p103 2 #{[1 2 3] :a "abc" "efg"})


;p105: Identify keys and values
;Given an input sequence of keywords and numbers, create a map such that each key in the map is a keyword,
;and the value is a sequence of all the numbers (if any) between it and the next keyword in the sequence
(def p105 (fn idk
            ([col] (idk {} col))
            ([result col] (idk result col (first col)))
            ([result col x]
             (if (empty? col)
               result
               (if (keyword? (first col))
                 (recur (assoc result (first col) []) (rest col) (first col))
                 (recur (assoc result x (conj (result x) (first col))) (rest col) x))))))
(p105 [])
(p105 [:a 1])
(p105 [:a 1, :b 2])
(p105 [:a 1 2 3 :b :c 4])


;p107: Simple closures
;Lexical scope and first-class functions are two of the most basic building blocks of a functional language like
;Clojure. When you combine the two together, you get something very powerful called lexical closures. With these,
;you can exercise a great deal of control over the lifetime of your local bindings, saving their values for use
;later, long after the code you're running now has finished.
;It can be hard to follow in the abstract, so let's build a simple closure. Given a positive integer n, return a
;function (f x) which computes x^n. Observe that the effect of this is to preserve the value of n for use outside
;the scope in which it is defined
;solution 1: recursion
(def p107 (fn [n]
            #((fn my-pow [res n]
                (if (= 0 n)
                  res
                  (recur (* res %) (- n 1))))
              1 n)))
((p107 2) 16)
(map (p107 3) [1 2 3 4])
(map #((p107 %) 2) [0 1 2 3 4])

;solution 2: round
(def p107_2 (fn [n]
              #(Math/round (Math/pow % n))))
((p107_2 2) 16)
(map (p107_2 3) [1 2 3 4])
(map #((p107_2 %) 2) [0 1 2 3 4])


;p108: Lazy Searching
;Given any number of sequences, each sorted from smallest to largest, find the smallest single number which appears
;in all of the sequences. The sequences may be infinite, so be careful to search lazily
(def p108 (fn [& args]
            (letfn [(p108_S [col X]
                      (= X (last (take-while #(<= % X) col))))]
              ((fn my-lz [N]
                 (if (nil? (nth (first args) N nil))
                   nil
                   (if (some #(false? %) (map p108_S (rest args) (repeat (nth (first args) N))))
                     (my-lz (+ N 1))
                     (nth (first args) N))))
               0))))
(p108 [1 2 3 4 5 6 7] [0.5 3/2 4 19])
(p108 (range) (range 0 100 7/6) [2 3 5 7 11 13])
(p108 (map #(* % % %) (range)) ;; perfect cubes
    (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
    (iterate inc 20))                                       ;64


;p110: Sequence of pronunciations
;Write a function that returns a lazy sequence of "pronunciations" of a sequence of numbers. A pronunciation of each
;element in the sequence consists of the number of repeating identical numbers and the number itself.
;For example, [1 1] is pronounced as [2 1] ("two ones"), which in turn is pronounced as [1 2 1 1] ("one two, one one").
;Your function should accept an initial sequence of numbers, and return an infinite lazy sequence of pronunciations,
;each element being a pronunciation of the previous element
(def p110 (fn [col]
            (letfn [(p110_S [col]
                      (reduce (fn [result x]
                                (conj result (count x) (first x)))
                              [] (partition-by identity col)))]
              ((fn my-sp [col]
                 (lazy-seq (cons (p110_S col) (my-sp (p110_S col)))))
               col))))
(take 3 (p110 [1]))
(first (p110 [1 1 1 4 4]))
(nth (p110 [1]) 6)
(count (nth (p110 [3 2]) 15))


;p112: Sequs Horribilis
;Create a function which takes an integer and a nested collection of integers as arguments. Analyze the elements
;of the input collection and return a sequence which maintains the nested structure, and which includes all
;elements starting from the head whose sum is less than or equal to the input integer
(def p112 (fn [N col]
             ((fn my-sh [col sm fcol]
                (if (empty? col)
                  fcol
                  (if (sequential? (first col))
                    (let [f (memoize (fn [col]
                                       (my-sh (first col) sm '())))]
                      (recur (next col) (+ sm (reduce + (flatten (f col)))) (concat fcol [(f col)])))
                    (if (< N (+ sm (first col)))
                      fcol
                      (recur (next col) (+ sm (first col)) (concat fcol [(first col)]))))))
              col 0 '())))
(p112 10 [1 2 [3 [4 5] 6] 7])
(p112 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])
(p112 9 (range))
(p112 1 [[[[[1]]]]])
(p112 0 [1 2 [3 [4 5] 6] 7])
(p112 0 [0 0 [0 [0]]])
(p112 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])


;p114: Global take-while
;take-while is great for filtering sequences, but it limited: you can only examine a single item of the sequence
;at a time. What if you need to keep track of some state as you go over the sequence?
;Write a function which accepts an integer n, a predicate p, and a sequence. It should return a lazy sequence
;of items in the list up to, but not including, the nth item that satisfies the predicate
(def p114 (fn [N pred col]
            ((fn gltake [result N i]
               (if (pred (nth col i))
                 (if (= N 1)
                   result
                   (lazy-seq (cons (nth col i) (gltake result (- N 1) (+ i 1)))))
                 (lazy-seq (cons (nth col i) (gltake result N (+ i 1))))))
             [] N 0)))
(p114 4 #(= 2 (mod % 3)) [2 3 5 7 11 13 17 19 23])
(p114 3 #(some #{\i} %) ["this" "is" "a" "sentence" "i" "wrote"])
(p114 1 #{"a"} ["this" "is" "a" "sentence" "i" "wrote"])


;p115: The Balance of N
;A balanced number is one whose component digits have the same sum on the left and right halves of the number.
;Write a function which accepts an integer n, and returns true iff n is balanced
(def p115 (fn [N]
            (let [toseq (memoize (fn [N] (map (comp read-string str) (str N))))
                  half (memoize (fn [N] (Math/round (Math/floor (/ (count (toseq N)) 2)))))]
              (= (apply + (take (half N) (toseq N))) (apply + (drop (if (odd? (count (toseq N)))
                                                                        (inc (half N))
                                                                        (half N))
                                                                      (toseq N)))))))
(p115 11)
(p115 121)
(p115 123)
(p115 0)
(p115 88099)
(p115 89098)
(p115 89089)
(take 20 (filter p115 (range)))


;p116: Prime Sandwich
;A balanced prime is a prime number which is also the mean of the primes directly before and after it in the
;sequence of valid primes. Create a function which takes an integer n, and returns true iff it is a balanced prime
(def p116 (fn [n]
            (letfn [(isPrime? [n]
                      (not (contains? (set (map #(mod n %) (range 2 (inc (/ n 2))))) 0)))]
              (if (and (not= 0 n) (not= 1 n) (not= 2 n) (isPrime? n))
                (letfn [(nextPrime [n]
                          (if (isPrime? n)
                            n
                            (recur (+ n 1))))
                        (prevPrime [n]
                          (if (isPrime? n)
                            n
                            (recur (- n 1))))]
                  (= n (/ (+ (nextPrime (+ n 1)) (prevPrime (- n 1))) 2)))
                false))))
(p116 4)
(p116 563)
(time (nth (filter p116 (range)) 100))
(p116 2)

;solution 2: time optimized --------------------------------------------

;get all primes <= n
(defn getPrimes
  ([n] (getPrimes n 2 []))
  ([n n0 result]
   (if (> n0 n)
     result
     (if (some #(= 0 %) (map #(mod n0 %) result))
       (recur n (+ n0 1) result)
       (recur n (+ n0 1) (conj result n0))))))

;get all primes < sqrt(n)
;exceptions: n = 2 or 3
(def sqrtPrimes (memoize (fn [n]
                           (if (or (= n 2) (= n 3))
                             [2]
                             (getPrimes (Math/round (Math/floor (Math/sqrt n))))))))

;check if n is prime
;n >= 2
;fm = sqrtPrimes(n)
(defn isPrime? [n fm]
  (if  (= n 2)
    true
    (not (contains? (set (map #(mod n %) fm)) 0))))

;get previous prime
;n > 2
;fm = sqrtPrimes(n)
(defn prevPrime [n fm]
  ((fn [n]
     (if (isPrime? n fm)
       n
       (recur (- n 1))))
   (- n 1)))

;get next prime
;n >= 2
;fm = sqrtPrimes(n)
(defn nextPrime [n fm]
  ((fn [n fm]
     (if (< n (Math/pow (last fm) 2))
       (if (isPrime? n fm)
         n
         (recur (+ n 1) fm))
       (recur n ((fn nextP [x]
                   (if (= (last fm) (last (getPrimes (inc x) x fm)))
                     (nextP (inc x))
                     (conj fm (last (getPrimes (inc x) x fm)))))
                 (last fm)))))
   (+ n 1) fm))


(def p116_2 (fn [n]
              (if (and (not= 0 n) (not= 1 n) (not= 2 n))
                (let [primes (sqrtPrimes n)]
                  (if (isPrime? n primes)
                    (= n (/ (+ (nextPrime n primes) (prevPrime n primes)) 2))
                    false))
                false)))
(p116_2 4)
(p116_2 563)
(time (nth (filter p116_2 (range)) 15))
;end of solution 2 ---------------------------------------------
