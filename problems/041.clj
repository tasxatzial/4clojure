;; p41: Drop Every Nth Item

;; Write a function which drops every Nth item from a sequence

(ns p41.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn drop-every-nth
  [xs N]
  (letfn [(not-nth-element? [idx]
            (pos? (mod (inc idx) N)))]
    (keep-indexed #(if (not-nth-element? %1) %2)
                  xs)))

(defn drop-every-nth2
  ([xs N]
   (drop-every-nth2 xs N 0 []))
  ([xs N idx result]
   (if (empty? xs)
     result
     (if (= 0 (mod (inc idx) N))
       (recur (rest xs) N (inc idx) result)
       (recur (rest xs) N (inc idx) (conj result (first xs)))))))

(deftest -drop-every-nth
  (testing "test1"
    (is (= (drop-every-nth [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])))
  (testing "test2"
    (is (= (drop-every-nth [:a :b :c :d :e :f] 2) [:a :c :e])))
  (testing "test3"
    (is (= (drop-every-nth [1 2 3 4 5 6] 4) [1 2 3 5 6]))))

(deftest test1-drop-every-nth2
  (testing "test1"
    (is (= (drop-every-nth2 [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])))
  (testing "test2"
    (is (= (drop-every-nth2 [:a :b :c :d :e :f] 2) [:a :c :e])))
  (testing "test3"
    (is (= (drop-every-nth2 [1 2 3 4 5 6] 4) [1 2 3 5 6]))))
