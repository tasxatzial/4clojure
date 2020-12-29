;p153: Pairwise Disjoint Sets
;Given a set of sets, create a function which returns true if no two of those sets have any elements
;in common and false otherwise. Some of the test cases are a bit tricky, so pay a little more attention
;to them.
;
(defn disjoint?
  "Returns true if no two sets in col have any elements in common, false otherwise."
  [col]
  (if (empty? col)
    true
    (let [first-set (first col)
          rest-sets (rest col)
          intersections (for [set rest-sets]
                          (clojure.set/intersection first-set set))]
      (if (not-every? empty? intersections)
        false
        (recur (rest col))))))

(= (disjoint? #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}})
   true)
(= (disjoint? #{#{:a :b :c :d :e}
         #{:a :b :c :d}
         #{:a :b :c}
         #{:a :b}
         #{:a}})
   false)
(= (disjoint? #{#{[1 2 3] [4 5]}
         #{[1 2] [3 4 5]}
         #{[1] [2] 3 4 5}
         #{1 2 [3 4] [5]}})
   true)
(= (disjoint? #{#{'a 'b}
         #{'c 'd 'e}
         #{'f 'g 'h 'i}
         #{''a ''c ''f}})
   true)
(= (disjoint? #{#{'(:x :y :z) '(:x :y) '(:z) '()}
         #{#{:x :y :z} #{:x :y} #{:z} #{}}
         #{'[:x :y :z] [:x :y] [:z] [] {}}})
   false)
(= (disjoint? #{#{(= "true") false}
         #{:yes :no}
         #{(class 1) 0}
         #{(symbol "true") 'false}
         #{(keyword "yes") ::no}
         #{(class '1) (int \0)}})
   false)
(= (disjoint? #{#{distinct?}
         #{#(-> %) #(-> %)}
         #{#(-> %) #(-> %) #(-> %)}
         #{#(-> %) #(-> %) #(-> %)}})
   true)
(= (disjoint? #{#{(#(-> *)) + (quote mapcat) #_nil}
         #{'+ '* mapcat (comment mapcat)}
         #{(do) set contains? nil?}
         #{#_empty?}})
   false)
