;; p40: Interpose a Seq

;; Write a function which separates the items of a sequence by an arbitrary value
;; restrictions: interpose

(ns p40.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn interpose-seq
  [N xs]
  (rest (apply concat (map #(vector N %) xs))))

(deftest tests
  (testing "test1"
    (is (= (interpose-seq 0 [1 2 3]) [1 0 2 0 3])))
  (testing "test2"
    (is (= (apply str (interpose-seq ", " ["one" "two" "three"])) "one, two, three")))
  (testing "test3"
    (is (= (interpose-seq :z [:a :b :c :d]) [:a :z :b :z :c :z :d]))))
