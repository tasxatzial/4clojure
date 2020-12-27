;p69: Merge with a Function
;Write a function which takes a function f and a variable number of maps. Your function should
;return a map that consists of the rest of the maps conj-ed onto the first. If a key occurs in
;more than one map, the mapping(s) from the latter (left-to-right) should be combined with the
;mapping in the result by calling (f val-in-result val-in-latter)
;
;restrictions: merge-with
;
(defn reduced-map
  "Returns a map that consists of cur-map conj-ed onto
   main-map.  If a key occurs in more than one map, the mapping(s)
   from the latter (left-to-right) will be combined with the mapping in
   the result by calling (func val-in-result val-in-latter)."
  [func main-map cur-map]
  (reduce (fn [result x]
            (let [key (get x 0)
                  value (get x 1)]
              (if (contains? main-map key)
                (assoc result key (func (main-map key) value))
                (conj result x))))
          {}
          cur-map))

(defn my-merge-with
  "Returns a map that consists of the rest of the maps conj-ed onto
   the first.  If a key occurs in more than one map, the mapping(s)
   from the latter (left-to-right) will be combined with the mapping in
   the result by calling (func val-in-result val-in-latter)."
  [& args]
  (let [func (first args)]
    (reduce (fn [result x]
              (conj result (reduced-map func result x)))
            (second args)
            (nnext args))))

(= (my-merge-with * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
   {:a 4, :b 6, :c 20})
(= (my-merge-with - {1 10, 2 20} {1 3, 2 10, 3 15})
   {1 7, 2 10, 3 15})
(= (my-merge-with concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
   {:a [3 4 5], :b [6 7], :c [8 9]})
