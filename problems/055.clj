;; p55: Count Occurrences

;; Write a function which returns a map containing the number of
;; occurrences of each distinct item in a sequence
;; restrictions: frequencies

(ns p55.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn frequencies-seq
  [xs]
  (reduce (fn [result x]
            (if (contains? result x)
              (update result x inc)
              (assoc result x 1)))
          {}
          xs))

(defn frequencies-seq2
  [xs]
  (->> xs
       (group-by identity)
       (map #(vector (first %) (count (second %))))
       (into {})))

(deftest tests-frequencies-seq
  (testing "test1"
    (is (= (frequencies-seq [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})))
  (testing "test2"
    (is (= (frequencies-seq [:b :a :b :a :b]) {:a 2, :b 3})))
  (testing "test3"
    (is (= (frequencies-seq '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2}))))

(deftest tests-frequencies-seq2
  (testing "test1"
    (is (= (frequencies-seq2 [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})))
  (testing "test2"
    (is (= (frequencies-seq2 [:b :a :b :a :b]) {:a 2, :b 3})))
  (testing "test3"
    (is (= (frequencies-seq2 '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2}))))
