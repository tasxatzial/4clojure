;p62: Re-implement Iterate
;Given a side-effect free function f and an initial value x write a function which returns an infinite
;lazy sequence of x, (f x), (f (f x)), (f (f (f x))), etc
;
;restrictions: iterate
;
(def p62 (fn my-iterate [func i]
           (cons i (lazy-seq (my-iterate func (func i))))))

;tests
(take 5 (p62 #(* 2 %) 1))
(take 100 (p62 inc 0))
(take 9 (p62 #(inc (mod % 3)) 1))