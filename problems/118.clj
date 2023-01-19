;; p118: Re-implement Map

;; Map is one of the core elements of a functional programming language.
;; Given a function f and an input sequence s, return a lazy sequence
;; of (f x) for each element x in s.
;; restrictions: map, map-indexed, mapcat, for

(ns p118.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn map-xs
  [f xs]
  (lazy-seq
    (when (seq xs)
      (cons (f (first xs)) (map-xs f (rest xs))))))

(deftest tests
  (testing "test1"
    (is (= [3 4 5 6 7]
           (map-xs inc [2 3 4 5 6]))))
  (testing "test2"
    (is (= (repeat 10 nil)
           (map-xs (fn [_] nil) (range 10)))))
  (testing "test3"
    (is (= [1000000 1000001]
           (->> (map-xs inc (range))
                (drop (dec 1000000))
                (take 2))))))
