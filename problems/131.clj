;p131: Sum Some Set Subsets
;Given a variable number of sets of integers, create a function which returns true iff all
;of the sets have a non-empty subset with an equivalent summation
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
  (reduce (fn [result x]
            (into result (conj (set-add result x) #{x})))
          #{#{}}
          set))

(defn power-set-sums
  "Returns a set which has the sum of all terms for each set of the power-set."
  [power-set]
  (reduce (fn [result x]
            (if (empty? x)
              result
              (conj result (reduce + x))))
          #{}
          power-set))

(defn p131
  [& col]
  (let [power-sets-sums (map power-set-sums (map power-set col))]
    (boolean (seq (apply clojure.set/intersection power-sets-sums)))))

;tests
(= true  (p131 #{-1 1 99}
             #{-2 2 888}
             #{-3 3 7777}))
(= false (p131 #{1}
             #{2}
             #{3}
             #{4}))
(= true  (p131 #{1}))
(= false (p131 #{1 -3 51 9}
             #{0}
             #{9 2 81 33}))
(= true  (p131 #{1 3 5}
             #{9 11 4}
             #{-3 12 3}
             #{-3 4 -2 10}))
(= false (p131 #{-1 -2 -3 -4 -5 -6}
             #{1 2 3 4 5 6 7 8 9}))
(= true  (p131 #{1 3 5 7}
             #{2 4 6 8}))
(= true  (p131 #{-1 3 -5 7 -9 11 -13 15}
             #{1 -3 5 -7 9 -11 13 -15}
             #{1 -1 2 -2 4 -4 8 -8}))
(= true  (p131 #{-10 9 -8 7 -6 5 -4 3 -2 1}
             #{10 -9 8 -7 6 -5 4 -3 2 -1}))
