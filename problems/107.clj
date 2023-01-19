;; p107: Simple closures

;; Lexical scope and first-class functions are two of the most basic building blocks of a
;; functional language like Clojure. When you combine the two together, you get something
;; very powerful called lexical closures. With these, you can exercise a great deal of control
;; over the lifetime of your local bindings, saving their values for use later, long after the
;; code you're running now has finished. It can be hard to follow in the abstract, so let's
;; build a simple closure. Given a positive integer n, return a function (f x) which computes
;; x^n. Observe that the effect of this is to preserve the value of n for use outside
;; the scope in which it is defined.

(ns p107.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn math-pow
  [n]
  (partial #(apply *' (repeat n %))))

(deftest tests
  (testing "test1"
    (is (= 256 ((math-pow 2) 16)
           ((math-pow 8) 2))))
  (testing "test2"
    (is (= [1 8 27 64] (map (math-pow 3) [1 2 3 4]))))
  (testing "test3"
    (is (= [1 2 4 8 16] (map #((math-pow %) 2) [0 1 2 3 4])))))
