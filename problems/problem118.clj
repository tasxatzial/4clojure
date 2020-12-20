;p118: Re-implement Map
;Map is one of the core elements of a functional programming language. Given a function f and an input sequence s,
;return a lazy sequence of (f x) for each element x in s
(def p118 (fn my-map [f col]
            (if (empty? col)
              nil
              (lazy-seq (cons (f (first col)) (my-map f (next col)))))))

;tests
(p118 inc [2 3 4 5 6])
(p118 (fn [_] nil) (range 10))
(->> (p118 inc (range))
     (drop (dec 1000000))
     (take 2))