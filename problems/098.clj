;; p98: Equivalence Classes

;; A function f defined on a domain D induces an equivalence relation on D, as follows:
;; a is equivalent to b with respect to f if and only if (f a) is equal to (f b).
;; Write a function with arguments f and D that computes the equivalence classes of D
;; with respect to f.

(ns p98.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn group-by-equivalence
  [f xs]
  (->> xs
       (group-by f)
       vals
       (map set)
       set))

(deftest tests
  (testing "test1"
    (is (= (group-by-equivalence #(* % %) #{-2 -1 0 1 2})
           #{#{0} #{1 -1} #{2 -2}})))
  (testing "test2"
    (is (= (group-by-equivalence #(rem % 3) #{0 1 2 3 4 5})
           #{#{0 3} #{1 4} #{2 5}})))
  (testing "test3"
    (is (= (group-by-equivalence identity #{0 1 2 3 4})
           #{#{0} #{1} #{2} #{3} #{4}})))
  (testing "test4"
    (is (= (group-by-equivalence (constantly true) #{0 1 2 3 4})
           #{#{0 1 2 3 4}}))))
