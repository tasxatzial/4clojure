;; p81: Set Intersection

;; Write a function which returns the intersection of two sets.
;; The intersection is the sub-set of items that each set has in common.
;; restrictions: intersection

(ns p81.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn sort-by-size
  "Sorts the given sets by their size (increasing)."
  [s1 s2]
  (if (> (count s1) (count s2))
    [s2 s1]
    [s1 s2]))

(defn set-intersection
  [s1 s2]
  (let [[min-set max-set] (sort-by-size s1 s2)]
    (reduce (fn [result x]
              (if (contains? max-set x)
                (conj result x)
                result))
            #{}
            min-set)))

(deftest tests
  (testing "test1"
    (is (= (set-intersection #{0 1 2 3} #{2 3 4 5}) #{2 3})))
  (testing "test2"
    (is (= (set-intersection #{0 1 2} #{3 4 5}) #{})))
  (testing "test3"
    (is (= (set-intersection #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d}))))
