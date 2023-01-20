;; p153: Pairwise Disjoint Sets

;; Given a set of sets, create a function which returns true if no two of those
;; sets have any elements in common and false otherwise. Some of the test cases
;; are a bit tricky, so pay a little more attention to them. Such sets are usually
;; called pairwise disjoint or mutually disjoint.

(ns p153.core
  (:require [clojure.test :refer [deftest testing is]]
            [clojure.set :as set]))

(defn pairwise-disjoint?
  [sets]
  (if (seq sets)
    (let [[first-set & rest-sets] sets
          intersections (map #(set/intersection first-set %) rest-sets)]
      (and (every? empty? intersections)
           (recur rest-sets)))
    true))

(deftest tests
  (testing "test1"
    (is (= (pairwise-disjoint? #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}})
           true)))
  (testing "test2"
    (is (= (pairwise-disjoint? #{#{:a :b :c :d :e}
                        #{:a :b :c :d}
                        #{:a :b :c}
                        #{:a :b}
                        #{:a}})
           false)))
  (testing "test3"
    (is (= (pairwise-disjoint? #{#{[1 2 3] [4 5]}
                        #{[1 2] [3 4 5]}
                        #{[1] [2] 3 4 5}
                        #{1 2 [3 4] [5]}})
           true)))
  (testing "test4"
    (is (= (pairwise-disjoint? #{#{'a 'b}
                        #{'c 'd 'e}
                        #{'f 'g 'h 'i}
                        #{''a ''c ''f}})
           true)))
  (testing "test5"
    (is (= (pairwise-disjoint? #{#{'(:x :y :z) '(:x :y) '(:z) '()}
                        #{#{:x :y :z} #{:x :y} #{:z} #{}}
                        #{'[:x :y :z] [:x :y] [:z] [] {}}})
           false)))
  (testing "test6"
    (is (= (pairwise-disjoint? #{#{(= "true") false}
                        #{:yes :no}
                        #{(class 1) 0}
                        #{(symbol "true") 'false}
                        #{(keyword "yes") ::no}
                        #{(class '1) (int \0)}})
           false)))
  (testing "test7"
    (is (= (pairwise-disjoint? #{#{distinct?}
                        #{#(-> %) #(-> %)}
                        #{#(-> %) #(-> %) #(-> %)}
                        #{#(-> %) #(-> %) #(-> %)}})
           true)))
  (testing "test8"
    (is (= (pairwise-disjoint? #{#{(#(-> *)) + (quote mapcat) #_nil}
                        #{'+ '* mapcat (comment mapcat)}
                        #{(do) set contains? nil?}
                        #{#_empty?}})
           false))))
