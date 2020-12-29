;p62: Re-implement Iterate
;Given a side-effect free function f and an initial value x write a function which
;returns an infinite lazy sequence of x, (f x), (f (f x)), (f (f (f x))), etc
;
;restrictions: iterate
;
(defn my-iterate
  "Returns a lazy sequence of x, (f x), (f (f x)) etc. f must be free of side-effects."
  [func i]
  (cons i (lazy-seq (my-iterate func (func i)))))

;tests
(= (take 5 (my-iterate #(* 2 %) 1)) [1 2 4 8 16])
(= (take 100 (my-iterate inc 0)) (take 100 (range)))
(= (take 9 (my-iterate #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3])))
