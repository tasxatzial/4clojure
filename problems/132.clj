;; p132: Insert between two items

;; Write a function that takes a two-argument predicate, a value, and a collection;
;; and returns a new collection where the value is inserted between every two items
;; that satisfy the predicate.

(ns p132.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn insert-between
  [pred val coll]
  (if (empty? coll)
    coll
    (letfn [(_step [coll last-added]
              (lazy-seq
                (when (seq coll)
                  (let [x (first coll)]
                    (if (pred last-added x)
                      (cons val (cons x (_step (rest coll) x)))
                      (cons x (_step (rest coll) x)))))))]
      (lazy-seq (cons (first coll) (_step (rest coll) (first coll)))))))

(deftest tests
  (testing "test1"
    (is (= '(1 :less 6 :less 7 4 3) (insert-between < :less [1 6 7 4 3]))))
  (testing "test2"
    (is (= '(2) (insert-between > :more [2]))))
  (testing "test3"
    (is (= [0 1 :x 2 :x 3 :x 4]  (insert-between #(and (pos? %) (< % %2)) :x (range 5)))))
  (testing "test4"
    (is (empty? (insert-between > :more ()))))
  (testing "test5"
    (is (= [0 1 :same 1 2 3 :same 5 8 13 :same 21]
           (take 12 (->> [0 1]
                         (iterate (fn [[a b]] [b (+ a b)]))
                         (map first) ; fibonacci numbers
                         (insert-between (fn [a b] ; both even or both odd
                                           (= (mod a 2) (mod b 2)))
                                         :same)))))))
