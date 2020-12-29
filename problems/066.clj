;p66: Greatest Common Divisor
;Given two integers, write a function which returns the greatest common divisor
;
(defn my-gcd
  "Returns the GCD of N1, N2."
  [N1 N2]
  (if (= 0 N2)
    N1
    (my-gcd N2 (mod N1 N2))))

;tests
(= (my-gcd 2 4) 2)
(= (my-gcd 10 5) 5)
(= (my-gcd 5 7) 1)
(= (my-gcd 1023 858) 33)
