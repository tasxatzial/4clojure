;; p77: Anagram Finder

;; Write a function which finds all the anagrams in a vector of words. A word x is
;; an anagram of word y if all the letters in x can be rearranged in a different
;; order to form y. Your function should return a set of sets, where each sub-set
;; is a group of words which are anagrams of each other. Each sub-set should have
;; at least two words. Words without any anagrams should not be included in the result.

(ns p77.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn get-sorted-chars
  "Splits a string into a sorted seq of chars."
  [s]
  (sort (map identity s)))

(defn group-by-anagrams
  [xs]
  (->> xs
       (group-by get-sorted-chars)
       (filter #(> (count (second %)) 1))
       (map (comp set second))
       set))

(deftest tests
  (testing "test1"
    (is (= (group-by-anagrams ["meat" "mat" "team" "mate" "eat"])
           #{#{"meat" "team" "mate"}})))
  (testing "test2"
    (is (= (group-by-anagrams ["veer" "lake" "item" "kale" "mite" "ever"])
           #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}))))
