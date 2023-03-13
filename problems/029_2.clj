;; p29: Get the Caps

;; Write a function which takes a string and returns a new string containing
;; only the capital letters.

(ns p29.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-capital
  [s]
  (apply str (re-seq #"[A-Z]" s)))

(deftest tests
  (testing "test1"
    (is (= (get-capital "HeLlO, WoRlD!") "HLOWRD")))
  (testing "test2"
    (is (empty? (get-capital "nothing"))))
  (testing "test3"
    (is (= (get-capital "$#A(*&987Zf") "AZ"))))
