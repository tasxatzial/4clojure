;; p116: Prime Sandwich

;; A balanced prime is a prime number which is also the mean of the primes directly
;; before and after it in the sequence of valid primes. Create a function which
;; takes an integer n, and returns true iff it is a balanced prime.

(defn prime?
  "Returns true if N is prime. This will happen if N is a multiple
  of any number in the given list of primes."
  [N primes]
  (reduce (fn [result prime]
            (if (> prime (inc (Math/sqrt N)))
              (reduced true)
              (if (= 0 (mod N prime))
                (reduced false)
                result)))
          true
          primes))

(defn get-primes
  "Returns all primes in [2, n]."
  [n]
  (loop [result []
         n0 2]
    (if (> n0 n)
      result
      (if (prime? n0 result)
        (recur (conj result n0) (inc n0))
        (recur result (inc n0))))))

(defn get-next-prime
  "Returns the next prime given a list of all previous primes."
  [primes]
  (if (empty? primes)
    2
    (loop [result (inc (peek primes))]
      (if (prime? result primes)
        result
        (recur (inc result))))))

(defn balanced-prime?
  "Returns true if N is a balanced prime, false otherwise."
  [N]
  (if (= N 2)
    false
    (let [primes (get-primes N)]
      (and (= (last primes) N)
           (let [prev-prime (get primes (- (count primes) 2))
                 next-prime (get-next-prime primes)]
             (= N (/ (+ prev-prime next-prime) 2)))))))

(deftest tests
  (testing "test1"
    (is (= false (balanced-prime? 4))))
  (testing "test2"
    (is (= true (balanced-prime? 563))))
  (testing "test3"
    (is (= 1103 (nth (filter balanced-prime? (range)) 15)))))
