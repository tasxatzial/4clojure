;p116: Prime Sandwich
;A balanced prime is a prime number which is also the mean of the primes directly
;before and after it in the sequence of valid primes. Create a function which
;takes an integer n, and returns true iff it is a balanced prime

(defn second-to-last
  "Returns the second to last element in col."
  [col]
  (if (>= (count col) 2)
    (nth col (- (count col) 2))
    nil))

(defn prime?
  "Returns true if N is prime. This will happen if N is a multiple
  of any number in the list of already known primes."
  [N primes]
  (if (empty? primes)
    true
    (let [current-prime (first primes)]
      (if (> current-prime (Math/sqrt N))
        true
        (if (= 0 (mod N current-prime))
          false
          (recur N (rest primes)))))))

(defn get-primes
  "Compute all primes <= n."
  ([n] (get-primes n 2 []))
  ([n n0 result]
   (if (> n0 n)
     result
     (if (prime? n0 result)
       (recur n (inc n0) (conj result n0))
       (recur n (inc n0) result)))))

(defn get-next-primes
  "Appends the next prime to primes."
  ([primes]
   (if (empty? primes)
     (get-next-primes primes 2)
     (get-next-primes primes (inc (last primes)))))
  ([primes N]
   (if (prime? N primes)
     (conj primes N)
     (recur primes (inc N)))))

(defn balanced-prime?
  "Returns true if N is a balanced prime, false otherwise."
  [N]
  (if (= N 2)
    false
    (let [primes (get-primes N)]
      (if (not= (last primes) N)
        false
        (let [prev-prime (second-to-last primes)
              new-primes (get-next-primes primes)
              next-prime (last new-primes)]
          (= N (Math/round ^double (/ (+ prev-prime next-prime) 2))))))))

;tests
(= false (balanced-prime? 4))
(= true (balanced-prime? 563))
(= 1103 (nth (filter balanced-prime? (range)) 15))
