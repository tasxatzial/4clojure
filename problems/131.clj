;; p131: Sum Some Set Subsets

;; Given a variable number of sets of integers, create a function which returns true
;; iff all of the sets have a non-empty subset with an equivalent summation.

(ns p131.core
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

(defn sum-per-coll
  "Sums the numbers in each of the colls and returns the result as
  a vector. Empty collections are excluded."
  [colls]
  (reduce (fn [result x]
            (if (empty? x)
              result
              (conj result (reduce + x))))
          []
          colls))

(defn p131
  [& colls]
  (->> colls
       (map (comp set sum-per-coll power-set))
       (apply set/intersection)
       seq
       boolean))

(deftest tests
  (testing "test1"
    (is (= true  (p131 #{-1 1 99}
                       #{-2 2 888}
                       #{-3 3 7777}))))
  (testing "test2"
    (is (= false (p131 #{1}
                       #{2}
                       #{3}
                       #{4}))))
  (testing "test3"
    (is (= true  (p131 #{1}))))
  (testing "test4"
    (is (= false (p131 #{1 -3 51 9}
                       #{0}
                       #{9 2 81 33}))))
  (testing "test5"
    (is (= true  (p131 #{1 3 5}
                       #{9 11 4}
                       #{-3 12 3}
                       #{-3 4 -2 10}))))
  (testing "test6"
    (is (= false (p131 #{-1 -2 -3 -4 -5 -6}
                       #{1 2 3 4 5 6 7 8 9}))))
  (testing "test7"
    (is (= true  (p131 #{1 3 5 7}
                       #{2 4 6 8}))))
  (testing "test8"
    (is (= true  (p131 #{-1 3 -5 7 -9 11 -13 15}
                       #{1 -3 5 -7 9 -11 13 -15}
                       #{1 -1 2 -2 4 -4 8 -8}))))
  (testing "test9"
    (is (= true  (p131 #{-10 9 -8 7 -6 5 -4 3 -2 1}
                       #{10 -9 8 -7 6 -5 4 -3 2 -1})))))
