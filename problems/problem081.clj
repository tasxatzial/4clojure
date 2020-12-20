;p81: Set Intersection
;Write a function which returns the intersection of two sets.
;The intersection is the sub-set of items that each set has in common
;
;restrictions: intersection

;solution 1 ----------------------------------------
(def p81 (fn [col1 col2]
           (reduce (fn [result x]
                     (if (and (contains? col1 x) (contains? col2 x))
                       (conj result x)
                       result))
                   #{} (into col1 col2))))

;solution 1 tests
(p81 #{0 1 2 3} #{2 3 4 5})
(p81 #{0 1 2} #{3 4 5})
(p81 #{:a :b :c :d} #{:c :e :a :f :d})

;solution2 ----------------------------------------
(def p81_2 (fn [col1 col2]
             (reduce (fn [result x]
                       (if (contains? col2 x)
                         (conj result x)
                         result))
                     #{} col1)))

;solution 2 tests
(p81_2 #{0 1 2 3} #{2 3 4 5})
(p81_2 #{0 1 2} #{3 4 5})
(p81_2 #{:a :b :c :d} #{:c :e :a :f :d})