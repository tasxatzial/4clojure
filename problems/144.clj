;; p144: Oscillate

;; Write an oscillating iterate: a function that takes an initial value and a variable
;; number of functions. It should return a lazy sequence of the functions applied to the
;; value in order, restarting from the first function after it hits the end.

(ns p144.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn oscillate
  [val & fns]
  (let [fns-count (count fns)]
    (letfn [(step [idx last-val]
              (lazy-seq
                (let [f (nth fns (mod idx fns-count))
                      fval (f last-val)]
                  (cons fval (step (inc idx) fval)))))]
      (lazy-seq (cons val (step 0 val))))))

(deftest tests
  (testing "test1"
    (is (= (take 3 (oscillate 3.14 int double)) [3.14 3 3.0])))
  (testing "test2"
    (is (= (take 5 (oscillate 3 #(- % 3) #(+ 5 %))) [3 0 5 2 7])))
  (testing "test3"
    (is (= (take 12 (oscillate 0 inc dec inc dec inc)) [0 1 0 1 0 1 2 1 2 1 2 3]))))
