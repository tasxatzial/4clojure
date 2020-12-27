;p81: Set Intersection
;Write a function which returns the intersection of two sets.
;The intersection is the sub-set of items that each set has in common
;
;restrictions: intersection

(defn my-intersection
  "Returns the intersection of two sets."
  [col1 col2]
  (reduce (fn [result x]
            (if (contains? col2 x)
              (conj result x)
              result))
          #{}
          col1))

(= (my-intersection #{0 1 2 3} #{2 3 4 5}) #{2 3})
(= (my-intersection #{0 1 2} #{3 4 5}) #{})
(= (my-intersection #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})
