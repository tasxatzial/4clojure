;p59: Juxtaposition
;Take a set of functions and return a new function that takes a variable number of arguments and returns a sequence
;containing the result of applying each function left-to-right to the argument list
;
;restrictions: juxt
;
(def p59 (fn [& f-args]
           (fn [& args]
             (map #(apply % args) f-args))))

;tests
((p59 + max min) 2 3 5 1 6 4)
((p59 #(.toUpperCase %) count) "hello")
((p59 :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})