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

;tests
(p75 1)
(p75 10)
(p75 40)
(p75 99)