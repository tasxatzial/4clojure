;; p141: Tricky card games

;; In trick-taking card games such as bridge, spades, or hearts, cards are played in
;; groups known as "tricks" - each player plays a single card, in order; the first player
;; is said to "lead" to the trick. After all players have played, one card is said to have
;; "won" the trick. How the winner is determined will vary by game, but generally the
;; winner is the highest card played in the suit that was led.
;; Sometimes (again varying by game), a particular suit will be designated "trump", meaning
;; that its cards are more powerful than any others: if there is a trump suit, and any
;; trumps are played, then the highest trump wins regardless of what was led.
;; Your goal is to devise a function that can determine which of a number of cards has won
;; a trick. You should accept a trump suit, and return a function winner. Winner will be
;; called on a sequence of cards, and should return the one which wins the trick. Cards will
;; be represented in the format returned by Problem 128, Recognize Playing Cards: a hash-map
;; of :suit and a numeric :rank. Cards with a larger rank are stronger.

(ns p141.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn p141
  [trump]
  (fn [coll]
    (let [trump (or trump (:suit (first coll)))]
      (reduce (fn [result x]
                (if (and (= trump (:suit x))
                         (> (:rank x) (:rank result)))
                  x
                  result))
              (first coll)
              (rest coll)))))

(deftest tests
  (testing "test1"
    (let [notrump (p141 nil)]
      (and (= {:suit :club :rank 9}  (notrump [{:suit :club :rank 4}
                                               {:suit :club :rank 9}]))
           (= {:suit :spade :rank 2} (notrump [{:suit :spade :rank 2}
                                               {:suit :club :rank 10}])))))
  (testing "test2"
    (is (= {:suit :club :rank 10} ((p141 :club) [{:suit :spade :rank 2}
                                                 {:suit :club :rank 10}]))))
  (testing "test3"
    (is (= {:suit :heart :rank 8}
           ((p141 :heart) [{:suit :heart :rank 6} {:suit :heart :rank 8}
                           {:suit :diamond :rank 10} {:suit :heart :rank 4}])))))
