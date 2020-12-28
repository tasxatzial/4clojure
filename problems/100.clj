;p100: Least Common Multiple
;Write a function which calculates the least common multiple. Your function should accept a variable number of
;positive integers or ratios

(defn my-gcd
  "Returns the GCD of x,y."
  [x y]
  (if (= 0 y)
    x
    (my-gcd y (mod x y))))

;solution 1
(defn my-lcm1
  "Returns the LCM of args."
  [& args]
  (let [max (apply max args)]
    ((fn lcm
       [i]
       (if (some #(not= 0 (mod i %)) args)
         (recur (+ max i))
         i))
     max)))

;solution 2: using GCD
(defn my-lcm2
  "Returns the LCM of args."
  [& args]
  (reduce (fn [result x]
            (/ (* result x) (my-gcd result x)))
          (first args) args))

(== (my-lcm1 2 3) 6)
(== (my-lcm1 5 3 7) 105)
(== (my-lcm1 1/3 2/5) 2)
(== (my-lcm1 3/4 1/6) 3/2)
(== (my-lcm1 7 5/7 2 3/5) 210)
