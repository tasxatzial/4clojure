;p26: Fibonacci Sequence
;Write a function which returns the first X fibonacci numbers
;
(defn first-N-fib
  "Returns the first N fibonacci numbers."
  [N]
  (letfn [(lazy-fib
            ([] (lazy-fib 0 1))
            ([prev next]
             (let [sum (+ prev next)]
               (cons prev (lazy-seq (lazy-fib next sum))))))]
    (take N (lazy-fib))))
