;; p128: Recognize Playing Cards

;; A standard American deck of playing cards has four suits - spades, hearts, diamonds,
;; and clubs - and thirteen cards in each suit. Two is the lowest rank, followed by other
;; integers up to ten; then the jack, queen, king, and ace. It's convenient for humans to
;; represent these cards as suit/rank pairs, such as H5 or DQ: the heart five and diamond
;; queen respectively. But these forms are not convenient for programmers, so to write a card
;; game you need some way to parse an input string into meaningful components. For purposes
;; of determining rank, we will define the cards to be valued from 0 (the two) to 12 (the ace)
;; Write a function which converts (for example) the string "SJ" into a map of
;; {:suit :spade, :rank 9}. A ten will always be represented with the single character "T",
;; rather than the two characters "10".

(ns p128.core
  (:require [clojure.test :refer [deftest testing is]]))

(def char-values
  {\S :spade
   \D :diamond
   \H :heart
   \C :club
   \2 0
   \3 1
   \4 2
   \5 3
   \6 4
   \7 5
   \8 6
   \9 7
   \T 8
   \J 9
   \Q 10
   \K 11
   \A 12})

(defn recognize-card
  [card]
  (let [[suit value] card]
    {:suit (char-values suit)
     :rank (char-values value)}))

(deftest tests
  (testing "test1"
    (is (= {:suit :diamond :rank 10} (recognize-card "DQ"))))
  (testing "test2"
    (is (= {:suit :heart :rank 3} (recognize-card "H5"))))
  (testing "test3"
    (is (= {:suit :club :rank 12} (recognize-card "CA"))))
  (testing "test4"
    (is (= (range 13) (map (comp :rank recognize-card str)
                           '[S2 S3 S4 S5 S6 S7
                             S8 S9 ST SJ SQ SK SA])))))
