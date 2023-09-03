;; p146: Trees into tables

;; Because Clojure's for macro allows you to "walk" over multiple sequences in a nested fashion,
;; it is excellent for transforming all sorts of sequences. If you don't want a sequence as your
;; final output (say you want a map), you are often still best-off using for, because you can
;; produce a sequence and feed it into a map, for example.
;; For this problem, your goal is to "flatten" a map of hashmaps. Each key in your output map
;; should be the "path" that you would have to take in the original map to get to a value, so
;; for example {1 {2 3}} should result in {[1 2] 3}. You only need to flatten one level of maps:
;; if one of the values is a map, just leave it alone.

(ns p146.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn tree-into-table
  [[a-key value]]
  (reduce (fn [result [inner-key inner-value]]
            (conj result [[a-key inner-key] inner-value]))
          {}
          value))

(defn trees-into-tables
  [tree]
  (into {} (map tree-into-table) tree))

(deftest tests
  (testing "test1"
    (is (= (trees-into-tables '{a {p 1, q 2}
                                b {m 3, n 4}})
           '{[a p] 1, [a q] 2
             [b m] 3, [b n] 4})))
  (testing "test2"
    (is (= (trees-into-tables '{[1] {a b c d}
                                [2] {q r s t u v w x}})
           '{[[1] a] b, [[1] c] d
             [[2] q] r, [[2] s] t
             [[2] u] v, [[2] w] x})))
  (testing "test3"
    (is (= (trees-into-tables '{m {1 [a b c] 3 nil}})
           '{[m 1] [a b c], [m 3] nil}))))
