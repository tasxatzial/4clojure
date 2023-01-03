;; p29: Get the Caps

;; Write a function which takes a string and returns a new string containing
;; only the capital letters.

(ns p29.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-capital
  [s]
  (letfn [(capital? [x]
            (<= (int \A) (int x) (int \Z)))]
    (apply str (filter capital? s))))

(defn get-capital2
  [s]
  (apply str (re-seq #"[A-Z]" s)))

(deftest tests-get-capital
  (testing "test1"
    (is (= (get-capital "HeLlO, WoRlD!") "HLOWRD")))
  (testing "test2"
    (is (empty? (get-capital "nothing"))))
  (testing "test3"
    (is (= (get-capital "$#A(*&987Zf") "AZ"))))

(deftest tests-get-capital2
  (testing "test1"
    (is (= (get-capital2 "HeLlO, WoRlD!") "HLOWRD")))
  (testing "test2"
    (is (empty? (get-capital2 "nothing"))))
  (testing "test3"
    (is (= (get-capital2 "$#A(*&987Zf") "AZ"))))
