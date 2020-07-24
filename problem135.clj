;p135: Infix Calculator
;Your friend Joe is always whining about Lisps using the prefix notation for math. Show him how you could easily
;write a function that does math using the infix notation. Is your favorite language that flexible, Joe? Write a
;function that accepts a variable length mathematical expression consisting of numbers and the operations +, -, *,
;and /. Assume a simple calculator that does not do precedence and instead just calculates left to right
(def p135 (fn [& args]
            ((fn [result args]
               (if (nil? (first args))
                 result
                 (recur ((first args) result (second args)) (drop 2 args))))
             (first args) (rest args))))

;tests
(p135 2 + 5)
(p135 38 + 48 - 2 / 2)
(p135 10 / 2 - 1 * 2)
(p135 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9)