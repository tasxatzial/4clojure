;; p103: Generating k-combinations

;; Given a sequence S consisting of n elements generate all k-combinations of S,
;; i. e. generate all possible sets consisting of k distinct elements taken from S.
;; The number of k-combinations for a sequence is equal to the binomial coefficient.

(ns p103.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn k-combinations-lazy
  [k coll]
  (letfn [(_step [k coll]
            (lazy-seq
              (when (seq coll)
                (if (= k 1)
                  (map list coll)
                  (concat (map #(cons (first coll) %)
                               (_step (dec k) (rest coll)))
                          (_step k (rest coll)))))))]
    (if (<= k 0)
      ()
      (_step k coll))))

(defn k-combinations
  [k coll]
  (set (map set (k-combinations-lazy k coll))))

(deftest tests
  (testing "test1"
    (is (= (k-combinations 1 #{4 5 6}) #{#{4} #{5} #{6}})))
  (testing "test2"
    (is (= (k-combinations 10 #{4 5 6}) #{})))
  (testing "test3"
    (is (= (k-combinations 2 #{0 1 2}) #{#{0 1} #{0 2} #{1 2}})))
  (testing "test4"
    (is (= (k-combinations 3 #{0 1 2 3 4}) #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                                             #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}})))
  (testing "test5"
    (is (= (k-combinations 4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}})))
  (testing "test6"
    (is (= (k-combinations 2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                                          #{:a "abc"} #{:a "efg"} #{"abc" "efg"}}))))
