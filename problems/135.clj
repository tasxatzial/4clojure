;p135: Infix Calculator
;Your friend Joe is always whining about Lisps using the prefix notation for math. Show him how
;you could easily write a function that does math using the infix notation. Is your favorite
;language that flexible, Joe? Write a function that accepts a variable length mathematical
;expression consisting of numbers and the operations +, -, *, and /. Assume a simple calculator
;that does not do precedence and instead just calculates left to right
;
(defn infix-calc
  "Compute expression from left to right. Precedence of operations is not taken
   into account."
  [& args]
  ((fn [result args]
     (let [op (first args)
           number (second args)]
       (if (nil? op)
         result
         (recur (op result number) (drop 2 args)))))
   (first args)
   (rest args)))

(= 7  (infix-calc 2 + 5))
(= 42 (infix-calc 38 + 48 - 2 / 2))
(= 8  (infix-calc 10 / 2 - 1 * 2))
(= 72 (infix-calc 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))
