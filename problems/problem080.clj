;p80: Perfect Numbers
;A number is "perfect" if the sum of its divisors equal the number itself. 6 is a perfect number because 1+2+3=6.
;Write a function which returns true for perfect numbers and false otherwise
(def p80 (fn my-perfect
           ([N] (my-perfect N 1 0))
           ([N I sum]
            (if (> I (/ N 2))
              (= sum N)
              (if (= 0 (mod N I))
                (recur N (+ I 1) (+ sum I))
                (recur N (+ I 1) sum))))))

;tests
(p80 6)
(p80 7)
(p80 496)
(p80 500)
(p80 8128)