;p75: Euler's Totient Function
;Two numbers are coprime if their greatest common divisor equals 1. Euler's totient function f(x)
;is defined as the number of positive integers less than x which are coprime to x. The special
;case f(1) equals 1. Write a function which calculates Euler's totient function
;
(defn my-gcd
  "Returns the GCD of N1, N2."
  [N1 N2]
  (if (= 0 N2)
    N1
    (my-gcd N2 (mod N1 N2))))

(defn totient
  "Calculates the number of positive integers less than N which are coprime to N.
   The special case totient(1) equals 1."
  [N]
  (if (= 1 N)
    1
    (let [gcds (map my-gcd (range 1 N) (repeat N))]
      (count (filter #(= 1 %) gcds)))))
