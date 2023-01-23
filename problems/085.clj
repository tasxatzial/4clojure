;; p85: Power Set

;; Write a function which generates the power set of a given set. The power set of a
;; set x is the set of all subsets of x, including the empty set and x itself.

(ns p85.core
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

(deftest tests
  (testing "test1"
    (is (= (power-set #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})))
  (testing "test2"
    (is (= (power-set #{}) #{#{}})))
  (testing "test3"
    (is (= (power-set #{1 2 3})
           #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})))
  (testing "test4"
    (is (= (count (power-set (into #{} (range 10)))) 1024))))
