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

;tests
(p114 4 #(= 2 (mod % 3)) [2 3 5 7 11 13 17 19 23])
(p114 3 #(some #{\i} %) ["this" "is" "a" "sentence" "i" "wrote"])
(p114 1 #{"a"} ["this" "is" "a" "sentence" "i" "wrote"])