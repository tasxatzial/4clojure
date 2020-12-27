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

(= (factorial1 1) 1)
(= (factorial1 3) 6)
(= (factorial1 5) 120)
(= (factorial1 8) 40320)
