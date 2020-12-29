;p42: Factorial Fun
;Write a function which calculates factorials

;solution 1
(defn factorial1
  "Returns the factorial of N."
  [N]
  (reduce * (range 1 (inc N))))

;solution 2
(defn factorial2
  "Returns the factorial of N."
  ([N] (factorial2 N 1))
  ([N result]
   (if (= N 1)
     result
     (recur (dec N) (* result N)))))

;tests
(= (factorial1 1) 1)
(= (factorial1 3) 6)
(= (factorial1 5) 120)
(= (factorial1 8) 40320)

(= (factorial2 1) 1)
(= (factorial2 3) 6)
(= (factorial2 5) 120)
(= (factorial2 8) 40320)
