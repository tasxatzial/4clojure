;p86: Happy numbers
;Happy numbers are positive integers that follow a particular formula: take each individual
;digit, square it, and then sum the squares to get a new number. Repeat with the new number
;and eventually, you might get to a number whose squared sum is 1. This is a happy number. 
;An unhappy number (or sad number) is one that loops endlessly.
;Write a function that determines if a number is happy or not
;
(defn squared-digit-sum
  "Returns the sum of the squares of the digits of N."
  [N]
  (let [digits (map (comp read-string str) (str N))]
    (reduce (fn [result x]
              (+ result (* x x)))
            0
            digits)))

(defn happy?
  "Returns true if N is a happy number, false otherwise."
  ([N] (happy? N #{}))
  ([N result]
   (if (contains? result N)
     false
     (if (= 1 N)
       true
       (recur (squared-digit-sum N) (conj result N))))))

;tests
(= (happy? 7) true)
(= (happy? 986543210) true)
(= (happy? 2) false)
(= (happy? 3) false)
