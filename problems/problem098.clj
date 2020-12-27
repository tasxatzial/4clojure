;p98: Equivalence Classes
;A function f defined on a domain D induces an equivalence relation on D, as follows:
;a is equivalent to b with respect to f if and only if (f a) is equal to (f b). Write a
;function with arguments f and D that computes the equivalence classes of D with respect to f
;
(defn group-by-equivalence
  "Computes the equivalence classes of col wtih respect to func."
  [func col]
  (reduce (fn [result x]
            (conj result (set (get x 1))))
          #{}
          (group-by func col)))

(= (group-by-equivalence #(* % %) #{-2 -1 0 1 2})
   #{#{0} #{1 -1} #{2 -2}})
(= (group-by-equivalence #(rem % 3) #{0 1 2 3 4 5})
   #{#{0 3} #{1 4} #{2 5}})
(= (group-by-equivalence identity #{0 1 2 3 4})
   #{#{0} #{1} #{2} #{3} #{4}})
(= (group-by-equivalence (constantly true) #{0 1 2 3 4})
   #{#{0 1 2 3 4}})
