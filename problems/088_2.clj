;; p88: Symmetric Difference

;; Write a function which returns the symmetric difference of two sets. The symmetric
;; difference is the set of items belonging to one but not both of the two sets.

(ns p88.core
  (:require
    [clojure.set :as set]
    [clojure.test :refer [deftest testing is]]))

(defn symmetric-difference
  [set1 set2]
  (let [intersection (set/intersection set1 set2)]
    (into (set/difference set1 intersection) (set/difference set2 intersection))))

(deftest tests
  (testing "test1"
    (is (= (symmetric-difference #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})))
  (testing "test2"
    (is (= (symmetric-difference #{:a :b :c} #{}) #{:a :b :c})))
  (testing "test3"
    (is (= (symmetric-difference #{} #{4 5 6}) #{4 5 6})))
  (testing "test4"
    (is (= (symmetric-difference #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]}))))
