;p153: Pairwise Disjoint Sets
;Given a set of sets, create a function which returns true if no two of those sets have any elements in common_1
;and false otherwise. Some of the test cases are a bit tricky, so pay a little more attention to them.
;_1 Such sets are usually called pairwise disjoint or mutually disjoint
(def p153 (fn [col]
            (if (empty? col)
              true
              (if (not-every? empty? (for [y (rest col)]
                                       (clojure.set/intersection (first col) y)))
                false
                (recur (rest col))))))

;tests
(p153 #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}})
(p153 #{#{:a :b :c :d :e}
        #{:a :b :c :d}
        #{:a :b :c}
        #{:a :b}
        #{:a}})
(p153 #{#{[1 2 3] [4 5]}
        #{[1 2] [3 4 5]}
        #{[1] [2] 3 4 5}
        #{1 2 [3 4] [5]}})
(p153 #{#{'a 'b}
        #{'c 'd 'e}
        #{'f 'g 'h 'i}
        #{''a ''c ''f}})
(p153 #{#{'(:x :y :z) '(:x :y) '(:z) '()}
        #{#{:x :y :z} #{:x :y} #{:z} #{}}
        #{'[:x :y :z] [:x :y] [:z] [] {}}})
(p153 #{#{(= "true") false}
        #{:yes :no}
        #{(class 1) 0}
        #{(symbol "true") 'false}
        #{(keyword "yes") ::no}
        #{(class '1) (int \0)}})
(p153 #{#{distinct?}
        #{#(-> %) #(-> %)}
        #{#(-> %) #(-> %) #(-> %)}
        #{#(-> %) #(-> %) #(-> %)}})
(p153 #{#{(#(-> *)) + (quote mapcat) #_ nil}
        #{'+ '* mapcat (comment mapcat)}
        #{(do) set contains? nil?}
        #{, , , #_, , empty?}})