;; p41: Drop Every Nth Item

;; Write a function which drops every Nth item from a sequence.

(ns p41.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn nth?
  [N idx]
  (zero? (mod (inc idx) N)))

;; lazy
(defn drop-every-nth
  [xs N]
  (let [nth-element? (partial nth? N)]
    (letfn [(drop-every-nth [xs idx]
              (lazy-seq
                (when (seq xs)
                  (if (nth-element? idx)
                    (drop-every-nth (rest xs) (inc idx))
                    (cons (first xs) (drop-every-nth (rest xs) (inc idx)))))))]
      (drop-every-nth xs 0))))

(deftest test1-drop-every-nth
  (testing "test1"
    (is (= (drop-every-nth [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])))
  (testing "test2"
    (is (= (drop-every-nth [:a :b :c :d :e :f] 2) [:a :c :e])))
  (testing "test3"
    (is (= (drop-every-nth [1 2 3 4 5 6] 4) [1 2 3 5 6]))))
