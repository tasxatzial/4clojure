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
;The parameter list should take a variable number of functions, and create a function that applies them from right-to-left
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
(def p62 (fn my-iterate [func i]
           (cons i (lazy-seq (my-iterate func (func i))))))
(take 5 (p62 #(* 2 %) 1))
(take 100 (p62 inc 0))
(take 9 (p62 #(inc (mod % 3)) 1))

