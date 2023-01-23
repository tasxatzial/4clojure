;; p103: Generating k-combinations

;; Given a sequence S consisting of n elements generate all k-combinations of S,
;; i. e. generate all possible sets consisting of k distinct elements taken from S.
;; The number of k-combinations for a sequence is equal to the binomial coefficient.

(ns p103.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn add-to
  "Adds x to every collection. Returns a set."
  [colls x]
  (->> colls
       (map #(conj % x))
       set))

(defn power-set
  "Returns the power set."
  [coll]
  (reduce (fn [res x]
            (->> #{x}
                 (conj (add-to res x))
                 (into res)))
          #{#{}}
          coll))

(defn generate-k-combinations
  [N coll]
  (->> (power-set coll)
       (filter #(= N (count %)))
       set))

(deftest tests
  (testing "test1"
    (is (= (generate-k-combinations 1 #{4 5 6}) #{#{4} #{5} #{6}})))
  (testing "test2"
    (is (= (generate-k-combinations 10 #{4 5 6}) #{})))
  (testing "test3"
    (is (= (generate-k-combinations 2 #{0 1 2}) #{#{0 1} #{0 2} #{1 2}})))
  (testing "test4"
    (is (= (generate-k-combinations 3 #{0 1 2 3 4}) #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                                                 #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}})))
  (testing "test5"
    (is (= (generate-k-combinations 4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}})))
  (testing "test6"
    (is (= (generate-k-combinations 2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                                              #{:a "abc"} #{:a "efg"} #{"abc" "efg"}}))))
