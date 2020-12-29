;p137: Digits and bases
;Write a function which returns a sequence of digits of a non-negative number (first argument)
;in numerical system with an arbitrary base (second argument). Digits should be represented
;with their integer values, e.g. 15 would be [1 5] in base 10, [1 1 1 1] in base 2 and [15]
;in base 16
;
(defn to-digits
  "Returns a sequence of digits of a non-negative number in numerical
   system with an arbitrary base."
  [number base]
  (if (= 0 number)
    '(0)
    ((fn [result num]
       (if (= 0 num)
         result
         (let [new-result (conj result (mod num base))
               new-num (int (/ num base))]
           (recur new-result new-num))))
     '()
     number)))

(= [1 2 3 4 5 0 1] (to-digits 1234501 10))
(= [0] (to-digits 0 11))
(= [1 0 0 1] (to-digits 9 2))
(= [1 0] (let [n (rand-int 100000)] (to-digits n n)))
(= [16 18 5 24 15 1] (to-digits Integer/MAX_VALUE 42))
