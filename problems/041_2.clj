;; p41: Drop Every Nth Item

;; Write a function which drops every Nth item from a sequence.

(ns p41.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn nth?
  [N idx]
  (zero? (mod (inc idx) N)))

(defn drop-every-nth
  [xs N]
  (let [nth-element? (partial nth? N)]
    (loop [xs xs
           idx 0
           result []]
      (if (empty? xs)
        result
        (if (nth-element? idx)
          (recur (rest xs) (inc idx) result)
          (recur (rest xs) (inc idx) (conj result (first xs))))))))

(deftest tests
  (testing "test1"
    (is (= (drop-every-nth [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])))
  (testing "test2"
    (is (= (drop-every-nth [:a :b :c :d :e :f] 2) [:a :c :e])))
  (testing "test3"
    (is (= (drop-every-nth [1 2 3 4 5 6] 4) [1 2 3 5 6]))))
