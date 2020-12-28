;p103: Generating k-combinations
;Given a sequence S consisting of n elements generate all k-combinations of S, i. e. generate
;all possible sets consisting of k distinct elements taken from S. The number of k-combinations
;for a sequence is equal to the binomial coefficient
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

(defn gen-k-combinations
  "Generates all possible sets consisting of N distinct elements taken from col."
  [N col]
  (set (filter #(= N (count %)) (power-set col))))

(= (gen-k-combinations 1 #{4 5 6}) #{#{4} #{5} #{6}})
(= (gen-k-combinations 10 #{4 5 6}) #{})
(= (gen-k-combinations 2 #{0 1 2}) #{#{0 1} #{0 2} #{1 2}})
(= (gen-k-combinations 3 #{0 1 2 3 4}) #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                                         #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}})
(= (gen-k-combinations 4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}})
(= (gen-k-combinations 2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                                      #{:a "abc"} #{:a "efg"} #{"abc" "efg"}})
