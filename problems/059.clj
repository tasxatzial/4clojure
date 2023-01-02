;; p59: Juxtaposition

;; Take a set of functions and return a new function that takes a variable number of
;; arguments and returns a sequence containing the result of applying each function
;; left-to-right to the argument list.
;; restrictions: juxt

(ns p59.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn juxt-fns
  [& fns]
  (fn [& args]
    (map #(apply % args) fns)))

(deftest tests
  (testing "test1"
    (is (= [21 6 1] ((juxt-fns + max min) 2 3 5 1 6 4))))
  (testing "test2"
    (is (= ["HELLO" 5] ((juxt-fns #(.toUpperCase %) count) "hello"))))
  (testing "test3"
    (is (= [2 6 4] ((juxt-fns :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})))))
