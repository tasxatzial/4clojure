;p148: The Big Divide
;Write a function which calculates the sum of all natural numbers under n (first argument) which are evenly
;divisible by at least one of a and b (second and third argument). Numbers a and b are guaranteed to be coprimes.
;Note: Some test cases have a very large n, so the most obvious solution will exceed the time limit
(def p148 (fn [N a b]
            (letfn [(f [X]
                      (let [q (quot (- N 1) X)]
                        (*' X (/ (*' q (+ 1 q)) 2))))]
              (- (+ (f a) (f b)) (f (* a b))))))

;tests
(p148 3 17 11)
(p148 10 3 5)
(p148 1000 3 5)
(str (p148 100000000 3 5))
(str (p148 (* 10000 10000 10000) 7 11))
(str (p148 (* 10000 10000 10000) 757 809))
(str (p148 (* 10000 10000 1000) 1597 3571))