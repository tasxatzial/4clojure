;p173: Intro to Destructuring 2
;Sequential destructuring allows you to bind symbols to parts of sequential things (vectors, lists, seqs, etc.):
;(let [bindings* ] exprs*) Complete the bindings so all let-parts evaluate to 3
(= 3
   (let [[x y] [+ (range 3)]] (apply x y))                  ;x y
   (let [[[x y] b] [[+ 1] 2]] (x y b))
   (let [[x y] [inc 2]] (x y)))