;p100: Least Common Multiple
;Write a function which calculates the least common multiple. Your function should accept a variable number of
;positive integers or ratios

;solution 1 ----------------------------------------
(def p100 (fn [& args]
            (let [maxN (memoize (fn [args] (apply max args)))]
              ((fn my-lcm[i]
                 (if (some #(not= 0 (mod i %)) args)
                   (recur (+ (maxN args) i))
                   i))
               (maxN args)))))

;solution 1 tests
(p100 2 3)
(p100 3 5 7)
(p100 1/3 2/5)
(p100 3/4 1/6)
(p100 7 5/7 2 3/5)

;solution 2 ----------------------------------------
(def p100_2 (fn [& args]
              (letfn [(my-gcd [x y]
                        (if (= 0 y)
                          x
                          (my-gcd y (mod x y))))]
                (reduce (fn [result x]
                          (/ (* result x) (my-gcd result x)))
                        (first args) args))))

;solution 2 tests
(p100_2 2 3)
(p100_2 3 5 7)
(p100_2 1/3 2/5)
(p100_2 3/4 1/6)
(p100_2 7 5/7 2 3/5)