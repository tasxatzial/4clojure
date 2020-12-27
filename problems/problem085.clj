;p85: Power Set
;Write a function which generates the power set of a given set. The power set of a
;set x is the set of all subsets of x, including the empty set and x itself
;
(defn set-add
  "Adds el to every set in col."
  [col el]
  (reduce (fn [result x]
            (conj result (conj x el)))
          #{}
          col))

(defn power-set
  "Returns the power set of set."
  [set]
  (let [power-set0 (reduce (fn [result x]
                             (into result (conj (set-add result x) #{x})))
                           #{}
                           set)]
    (conj power-set0 #{})))

(= (power-set #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})
(= (power-set #{}) #{#{}})
(= (power-set #{1 2 3})
   #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
(= (count (power-set (into #{} (range 10)))) 1024)
