;p78: Reimplement Trampoline
;Reimplement the function trampoline."
;
;restrictions: trampoline
;
(defn my-trampoline
  "See clojure.core/trampoline."
  ([f & args]
   (if (empty? args)
     (my-trampoline f)
     (my-trampoline (apply f args))))
  ([f]
   (if (fn? f)
     (my-trampoline (f))
     f)))

;tests
(= (letfn [(triple [x] #(sub-two (* 3 x)))
           (sub-two [x] #(stop? (- x 2)))
           (stop? [x] (if (> x 50) x #(triple x)))]
     (my-trampoline triple 2))
   82)
(= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
           (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
     (map (partial my-trampoline my-even?) (range 6)))
   [true false true false true false])
