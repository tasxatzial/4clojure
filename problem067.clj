;p67: Prime Numbers
;Write a function which returns the first x number of prime numbers
(def p67 (fn my-primes
           ([x] (my-primes x [] 2))
           ([x result N]
            (if (= x 0)
              result
              (if (contains? (set (map #(mod N %) result)) 0)
                (my-primes x result (+ N 1))
                (my-primes (- x 1) (conj result N) (+ N 1)))))))

;tests
(p67 2)
(p67 5)
(last (p67 100))