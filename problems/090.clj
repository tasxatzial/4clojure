;; p90: Cartesian Product

;; Write a function which calculates the Cartesian product of two sets.

(ns p90.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn cartesian-product
  [set1 set2]
  (set (for [x set1
             y set2]
         [x y])))

(deftest tests
  (testing "test1"
    (is (= (cartesian-product #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
           #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
             ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
             ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})))
  (testing "test2"
    (is (= (cartesian-product #{1 2 3} #{4 5})
           #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})))
  (testing "test3"
    (is (= 300 (count (cartesian-product (into #{} (range 10))
                                         (into #{} (range 30))))))))
