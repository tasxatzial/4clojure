;; p70: Word Sorting

;; Write a function that splits a sentence up into a sorted list of words.
;; Capitalization should not affect sort order and punctuation should be ignored

(ns p70.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn word-split
  [s]
  (re-seq #"\w+" s))

(defn word-sort
  [s]
  (->> s 
       word-split 
       (sort-by clojure.string/lower-case)))

(deftest tests
  (testing "test1"
    (is (= (word-sort  "Have a nice day.") ["a" "day" "Have" "nice"])))
  (testing "test2"
    (is (= (word-sort  "Clojure is a fun language!")
           ["a" "Clojure" "fun" "is" "language"])))
  (testing "test3"
    (is (= (word-sort  "Fools fall for foolish follies.")
           ["fall" "follies" "foolish" "Fools" "for"]))))
