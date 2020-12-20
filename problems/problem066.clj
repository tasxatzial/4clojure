;p66: Greatest Common Divisor
;Given two integers, write a function which returns the greatest common divisor
(def p66 (fn my-gcd [N1 N2]
           (if (= 0 N2)
             N1
             (my-gcd N2 (mod N1 N2)))))

;tests
(p66 2 4)
(p66 10 5)
(p66 5 7)
(p66 1023 858)