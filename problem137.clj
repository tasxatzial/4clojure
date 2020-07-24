;p137: Digits and bases
;Write a function which returns a sequence of digits of a non-negative number (first argument) in numerical system
;with an arbitrary base (second argument). Digits should be represented with their integer values,
;e.g. 15 would be [1 5] in base 10, [1 1 1 1] in base 2 and [15] in base 16
(def p137 (fn [number base]
            (if (= 0 number)
              '(0)
              ((fn [result num]
                 (if (= 0 num)
                   result
                   (recur (conj result (mod num base)) (int (/ num base)))))
               '() number))))

;tests
(p137 1234501 10)
(p137 0 11)
(p137 9 2)
(let [n (rand-int 100000)](p137 n n))
(p137 Integer/MAX_VALUE 42)