;; p84: Transitive Closure

;; Write a function which generates the transitive closure of a binary relation.
;; The relation will be represented as a set of 2 item vectors.

(defn find-relations
  "Adds to coll the relations of v with each item in coll."
  [v coll]
  (reduce (fn [result x]
            (let [[vf vs] v
                  [xf xs] x
                  rel1 (when (= xs vf) [xf vs])
                  rel2 (when (= vs xf) [vf xs])]
              (remove nil? (into result [rel1 rel2]))))
          coll
          coll))

(defn transitive-closure-single-pass
  "Adds to coll the relations of each of its items with the rest of the coll."
  [coll]
  (loop [coll coll
         result coll]
    (if (seq coll)
      (let [relations (find-relations (first coll) (rest coll))]
        (recur (rest coll) (into result relations)))
      result)))

(defn transitive-closure
  [coll]
  (let [single-pass-closure (transitive-closure-single-pass coll)]
    (if (= single-pass-closure coll)
      coll
      (recur single-pass-closure))))

(deftest tests
  (testing "test1"
    (let [divides #{[8 4] [9 3] [4 2] [27 9]}]
      (is (= (transitive-closure divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}))))
  (testing "test2"
    (let [more-legs
          #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
      (is (= (transitive-closure more-legs)
             #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
               ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}))))
  (testing "test3"
    (let [progeny
          #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
      (is (= (transitive-closure progeny)
             #{["father" "son"] ["father" "grandson"]
               ["uncle" "cousin"] ["son" "grandson"]})))))
