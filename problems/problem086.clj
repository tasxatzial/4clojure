;p86: Happy numbers
;Happy numbers are positive integers that follow a particular formula: take each individual digit, square it, and
;then sum the squares to get a new number. Repeat with the new number and eventually, you might get to a number
;whose squared sum is 1. This is a happy number. An unhappy number (or sad number) is one that loops endlessly.
;Write a function that determines if a number is happy or not
(def p86 (fn [N]
           (letfn [(sumd [N]
                     (reduce (fn [result x]
                               (+ result (* x x)))
                             0 (map (comp read-string str) (str N))))]
             ((fn myf [N_ result]
                (if (contains? result N_)
                  false
                  (if (= 1 N_)
                    true
                    (recur (sumd N_) (conj result N_)))))
              (sumd N) #{}))))

;tests
(p86 7)
(p86 986543210)
(p86 2)
(p86 3)