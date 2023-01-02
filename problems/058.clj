;; p58: Function Composition

;; Write a function which allows you to create function compositions.
;; The parameter list should take a variable number of functions, and create a function
;; that applies them from right-to-left.
;; restrictions: comp

(ns p58.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn comp-two
  "Compose two functions."
  [f1 f2]
  (fn [& args]
    (f1 (apply f2 args))))

(defn comp-any
  "Compose any number of functions."
  [& fns]
  (fn [& args]
    (if (= 1 (count fns))
      (apply (first fns) args)
      ((first fns) (apply (apply comp-any (rest fns)) args)))))

(deftest tests
  (testing "test1"
    (is (= [3 2 1] ((comp-any rest reverse) [1 2 3 4]))))
  (testing "test2"
    (is (= 5 ((comp-any (partial + 3) second) [1 2 3 4]))))
  (testing "test3"
    (is (= true ((comp-any zero? #(mod % 8) +) 3 5 7 9))))
  (testing "test4"
    (is (= "HELLO" ((comp-any #(.toUpperCase %) #(apply str %) take) 5 "hello world")))))
