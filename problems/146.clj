;p146: Trees into tables
;Because Clojure's for macro allows you to "walk" over multiple sequences in a nested fashion, it is
;excellent for transforming all sorts of sequences. If you don't want a sequence as your final output
;(say you want a map), you are often still best-off using for, because you can produce a sequence and
;feed it into a map, for example.

;For this problem, your goal is to "flatten" a map of hashmaps. Each key in your output map should be
;the "path" that you would have to take in the original map to get to a value, so for example {1 {2 3}}
;should result in {[1 2] 3}. You only need to flatten one level of maps: if one of the values is a map,
;just leave it alone.

(defn tree-into-table
  "Accepts a vector where the first element is the key of a map and the second
   one is the value of the key. The value is itself a map. Returns a new value
   map but each key now is a vector that has both the key of the map and the
   key of an element in the value map. Example: [a {b 1, c 2}] returns {[a b] 1, [a c] 2}."
  [[key val]]
  (reduce (fn [result [inner-key inner-val]]
            (into result {[key inner-key] inner-val}))
          {}
          val))

(defn trees-into-tables
  "Flattens (one level) a map of hashmaps. Example: {1 {2 3}} returns {[1 2] 3}."
  [col]
  (reduce (fn [result x]
            (into result (tree-into-table x)))
          {}
          col))

;tests
(= (trees-into-tables '{a {p 1, q 2}
         b {m 3, n 4}})
   '{[a p] 1, [a q] 2
     [b m] 3, [b n] 4})
(= (trees-into-tables '{[1] {a b c d}
         [2] {q r s t u v w x}})
   '{[[1] a] b, [[1] c] d
     [[2] q] r, [[2] s] t
     [[2] u] v, [[2] w] x})
(= (trees-into-tables '{m {1 [a b c] 3 nil}})
   '{[m 1] [a b c], [m 3] nil})
