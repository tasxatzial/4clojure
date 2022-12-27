;; p40: Interpose a Seq

;; Write a function which separates the items of a sequence by an arbitrary value
;; restrictions: interpose

(defn interpose-seq
  [N xs]
  (butlast (reduce into [] (map #(vector % N) xs))))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (interpose-seq 0 [1 2 3]) [1 0 2 0 3]))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (apply str (interpose-seq ", " ["one" "two" "three"])) "one, two, three"))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (interpose-seq :z [:a :b :c :d]) [:a :z :b :z :c :z :d]))))
