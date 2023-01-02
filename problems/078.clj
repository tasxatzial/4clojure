;; p78: Reimplement Trampoline

;; The trampoline function takes a function f and a variable number of
;; parameters. Trampoline calls f with any parameters that were supplied.
;; If f returns a function, trampoline calls that function with no arguments.
;; This is repeated, until the return value is not a function, and then
;; trampoline returns that non-function value. This is useful for implementing
;; mutually recursive algorithms in a way that won't consume the stack.
;; restrictions: trampoline

(ns p78.core
  (:require [clojure.test :as test :refer [deftest testing is]]))

(defn trampoline-fns
  ([f & args]
   (if (empty? args)
     (if (test/function? f)
       (trampoline-fns (f))
       f)
     (trampoline-fns (apply f args)))))

(deftest tests
  (testing "test1"
    (is (= (letfn [(triple [x] #(sub-two (* 3 x)))
                   (sub-two [x] #(stop? (- x 2)))
                   (stop? [x] (if (> x 50) x #(triple x)))]
             (trampoline-fns triple 2))
           82)))
  (testing "test2"
    (is (= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
                   (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
             (map (partial trampoline-fns my-even?) (range 6)))
           [true false true false true false]))))
