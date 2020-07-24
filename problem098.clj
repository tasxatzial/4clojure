;p98: Equivalence Classes
;A function f defined on a domain D induces an equivalence relation on D, as follows: a is equivalent to b with
;respect to f if and only if (f a) is equal to (f b). Write a function with arguments f and D that computes the
;equivalence classes of D with respect to f
(def p98 (fn [func col]
           (reduce (fn [result x]
                     (conj result (set (x 1))))
                   #{} (group-by func col))))

;tests
(p98 #(* % %) #{-2 -1 0 1 2})
(p98 #(rem % 3) #{0 1 2 3 4 5})
(p98 identity #{0 1 2 3 4})
(p98 (constantly true) #{0 1 2 3 4})