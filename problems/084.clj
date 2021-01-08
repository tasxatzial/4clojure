;p84
;Write a function which generates the transitive closure of a binary relation. The relation will
;be represented as a set of 2 item vectors.
;
(defn find-relation
  "Produces a new relation from vec1 and vec2. The new relation is nil
  if the second item of vec1 is different that the first item of
  vec1 otherwise it is [(first vec1) (second vec2)]."
  [vec1 vec2]
  (if (= (second vec1) (first vec2))
    [(first vec1) (second vec2)]
    nil))

(defn find-relations
  "Produces all the new relations of vec with each item in col. The order
  of the item does not matter."
  [vec col]
  (reduce (fn [result x]
            (let [relation1 (find-relation vec x)
                  relation2 (find-relation x vec)]
              (if relation1
                (conj result relation1)
                (if relation2
                  (conj result relation2)
                  result))))
          col
          col))

(defn transitive-closure-one-pass
  "Produces all new relations of each item in col with the rest
  of the col. The order of the items does not matter."
  ([col] (transitive-closure-one-pass col col))
  ([col result]
   (if (empty? col)
     result
     (let [vec (first col)
           relations (find-relations vec (rest col))]
       (recur (rest col) (into result relations))))))

(defn transitive-clojure
  "Returns the transitive closure of a binary relation. The relation is
  represented as a set of 2 item vectors."
  [col]
  (let [one-pass-closure (transitive-closure-one-pass col)]
    (if (= one-pass-closure col)
      col
      (recur one-pass-closure))))

;tests
(let [divides #{[8 4] [9 3] [4 2] [27 9]}]
  (= (transitive-clojure divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}))
(let [more-legs
      #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
  (= (transitive-clojure more-legs)
     #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
       ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}))
(let [progeny
      #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
  (= (transitive-clojure progeny)
     #{["father" "son"] ["father" "grandson"]
       ["uncle" "cousin"] ["son" "grandson"]}))
