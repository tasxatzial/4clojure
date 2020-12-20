;p116: Prime Sandwich
;A balanced prime is a prime number which is also the mean of the primes directly before and after it in the
;sequence of valid primes. Create a function which takes an integer n, and returns true iff it is a balanced prime

;solution 1
(def p116 (fn [n]
            (letfn [(isPrime? [n]
                      (not (contains? (set (map #(mod n %) (range 2 (inc (/ n 2))))) 0)))]
              (if (and (not= 0 n) (not= 1 n) (not= 2 n) (isPrime? n))
                (letfn [(nextPrime [n]
                          (if (isPrime? n)
                            n
                            (recur (+ n 1))))
                        (prevPrime [n]
                          (if (isPrime? n)
                            n
                            (recur (- n 1))))]
                  (= n (/ (+ (nextPrime (+ n 1)) (prevPrime (- n 1))) 2)))
                false))))

;solution 1 tests
(p116 4)
(p116 563)
(time (nth (filter p116 (range)) 100))
(p116 2)

;solution 2 (faster)

;get all primes <= n
(defn getPrimes
  ([n] (getPrimes n 2 []))
  ([n n0 result]
   (if (> n0 n)
     result
     (if (some #(= 0 %) (map #(mod n0 %) result))
       (recur n (+ n0 1) result)
       (recur n (+ n0 1) (conj result n0))))))

;get all primes < sqrt(n)
;exceptions: n = 2 or 3
(def sqrtPrimes (memoize (fn [n]
                           (if (or (= n 2) (= n 3))
                             [2]
                             (getPrimes (Math/round (Math/floor (Math/sqrt n))))))))

;check if n is prime
;n >= 2
;fm = sqrtPrimes(n)
(defn isPrime? [n fm]
  (if  (= n 2)
    true
    (not (contains? (set (map #(mod n %) fm)) 0))))

;get previous prime
;n > 2
;fm = sqrtPrimes(n)
(defn prevPrime [n fm]
  ((fn [n]
     (if (isPrime? n fm)
       n
       (recur (- n 1))))
   (- n 1)))

;get next prime
;n >= 2
;fm = sqrtPrimes(n)
(defn nextPrime [n fm]
  ((fn [n fm]
     (if (< n (Math/pow (last fm) 2))
       (if (isPrime? n fm)
         n
         (recur (+ n 1) fm))
       (recur n ((fn nextP [x]
                   (if (= (last fm) (last (getPrimes (inc x) x fm)))
                     (nextP (inc x))
                     (conj fm (last (getPrimes (inc x) x fm)))))
                 (last fm)))))
   (+ n 1) fm))

(def p116_2 (fn [n]
              (if (and (not= 0 n) (not= 1 n) (not= 2 n))
                (let [primes (sqrtPrimes n)]
                  (if (isPrime? n primes)
                    (= n (/ (+ (nextPrime n primes) (prevPrime n primes)) 2))
                    false))
                false)))

;solution 2 tests
(p116_2 4)
(p116_2 563)
(time (nth (filter p116_2 (range)) 15))