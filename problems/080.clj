;p80: Perfect Numbers
;A number is "perfect" if the sum of its divisors equal the number itself. 6 is a perfect number because 1+2+3=6.
;Write a function which returns true for perfect numbers and false otherwise
;
(defn perfect?
  "Returns true if N is a perfect number, false otherwise."
  ([N] (perfect? N 1 0))
  ([N I sum]
   (if (> I (/ N 2))
     (= sum N)
     (if (= 0 (mod N I))
       (recur N (inc I) (+ sum I))
       (recur N (inc I) sum)))))

;tests
(= (perfect? 6) true)
(= (perfect? 7) false)
(= (perfect? 496) true)
(= (perfect? 500) false)
(= (perfect? 8128) true)
