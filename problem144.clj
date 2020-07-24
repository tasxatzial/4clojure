;p144: Oscilrate
;Write an oscillating iterate: a function that takes an initial value and a variable number of functions. It should
;return a lazy sequence of the functions applied to the value in order, restarting from the first function after
;it hits the end
(def p144 (fn [val & args]
            ((fn oscil [val idx]
               (cons val (lazy-seq (oscil ((nth args (mod idx (count args))) val) (+ idx 1)))))
             val 0)))

;tests
(take 5 (p144 3.14 int double))
(take 5 (p144 3 #(- % 3) #(+ 5 %)))
(take 12 (p144 0 inc dec inc dec inc))