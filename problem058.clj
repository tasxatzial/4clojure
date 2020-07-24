;p58: Function Composition
;Write a function which allows you to create function compositions.
;The parameter list should take a variable number of functions, and create a function that applies them from right-to-left
;
;restrictions: comp

;compose two functions
(def p58_tmp (fn my-comp [f1 f2]
           (fn [& args]
             (f1 (apply f2 args)))))

;compose any number of functions
(def p58_2 (fn my-comp [& f-args]
             (fn [& args]
               (if (= 1 (count f-args))
                 (apply (first f-args) args)
                 ((first f-args) (apply (apply my-comp (rest f-args)) args))))))

;tests
((p58_2 rest reverse) [1 2 3 4])
((p58_2 (partial + 3) second) [1 2 3 4])
((p58_2 zero? #(mod % 8) +) 3 5 7 9)
((p58_2 #(.toUpperCase %) #(apply str %) take) 5 "hello world")