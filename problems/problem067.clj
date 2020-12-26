;p67: Prime Numbers
;Write a function which returns the first x number of prime numbers
;
(defn my-primes
  "Returns the first x prime numbers."
  ([x] (my-primes x [] 2))
  ([x result N]
   (if (= x 0)
     result
     (if (contains? (set (map #(mod N %) result)) 0)
       (recur x result (inc N)) ; N is composite
       (recur (dec x) (conj result N) (inc N)))))) ; N is prime
