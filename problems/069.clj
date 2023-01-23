;; p69: Merge with a Function

;; Write a function which takes a function f and a variable number of maps.
;; Your function should return a map that consists of the rest of the maps conj-ed
;; onto the first. If a key occurs in more than one map, the mapping(s) from the
;; latter (left-to-right) should be combined with the mapping in the result by
;; calling (f val-in-result val-in-latter).
;; restrictions: merge-with

(ns p69.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn merge-maps-with
  [f & args]
  (let [keys-set (set (apply concat (map keys args)))]
    (reduce (fn [result _key]
              (let [_vals (filter some? (map #(% _key) args))]
                (if (= 1 (count _vals))
                  (assoc result _key (first _vals))
                  (assoc result _key (apply f _vals)))))
            {}
            keys-set)))

(deftest tests
  (testing "test1"
    (is (= (merge-maps-with * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
           {:a 4, :b 6, :c 20})))
  (testing "test2"
    (is (= (merge-maps-with - {1 10, 2 20} {1 3, 2 10, 3 15})
           {1 7, 2 10, 3 15})))
  (testing "test3"
    (is (= (merge-maps-with concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
           {:a [3 4 5], :b [6 7], :c [8 9]}))))
