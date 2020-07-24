;p78: Reimplement Trampoline
;Reimplement the function described in "Intro to Trampoline" (problem 76)
;
;restrictions: trampoline
;
(def p78 (fn my-trampoline
           ([fn1 & args]
            (if (empty? args)
              (my-trampoline fn1)
              (my-trampoline (apply fn1 args))))
           ([fn1]
            (if (fn? fn1)
              (my-trampoline (fn1))
              fn1))))

;tests
(= (letfn [(triple [x] #(sub-two (* 3 x)))
           (sub-two [x] #(stop?(- x 2)))
           (stop? [x] (if (> x 50) x #(triple x)))]
     (p78 triple 2))
   82)
(= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
           (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
     (map (partial p78 my-even?) (range 6)))
   [true false true false true false])