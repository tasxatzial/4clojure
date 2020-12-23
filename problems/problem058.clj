;p58: Function Composition
;Write a function which allows you to create function compositions.
;The parameter list should take a variable number of functions, and create a function
;that applies them from right-to-left
;
;restrictions: comp

(defn my-comp2
  "Compose two functions."
  [f1 f2]
  (fn [& args]
    (f1 (apply f2 args))))

(defn my-comp
  "Compose any number of functions."
  [& f-args]
  (fn [& args]
    (if (= 1 (count f-args))
      (apply (first f-args) args)
      ((first f-args) (apply (apply my-comp (rest f-args)) args)))))

(= [3 2 1] ((my-comp rest reverse) [1 2 3 4]))
(= 5 ((my-comp (partial + 3) second) [1 2 3 4]))
(= true ((my-comp zero? #(mod % 8) +) 3 5 7 9))
(= "HELLO" ((my-comp #(.toUpperCase %) #(apply str %) take) 5 "hello world"))
