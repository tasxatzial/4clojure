;p128: Recognize Playing Cards
;A standard American deck of playing cards has four suits - spades, hearts, diamonds,
;and clubs - and thirteen cards in each suit. Two is the lowest rank, followed by other
;integers up to ten; then the jack, queen, king, and ace. It's convenient for humans to
;represent these cards as suit/rank pairs, such as H5 or DQ: the heart five and diamond
;queen respectively. But these forms are not convenient for programmers, so to write a card
;game you need some way to parse an input string into meaningful components. For purposes
;of determining rank, we will define the cards to be valued from 0 (the two) to 12 (the ace)
;Write a function which converts (for example) the string "SJ" into a map of
;{:suit :spade, :rank 9}. A ten will
;always be represented with the single character "T", rather than the two characters "10"
;
(def cards-map
  {\S :spade \D :diamond \H :heart \C :club
   \2 0 \3 1 \4 2 \5 3 \6 4 \7 5 \8 6 \9 7 \T 8 \J 9 \Q 10 \K 11 \A 12})

(defn recognize-card
  [card]
  (let [suit (get card 0)
        value (get card 1)]
    {:suit (cards-map suit) :rank (cards-map value)}))

;tests
(= {:suit :diamond :rank 10} (recognize-card "DQ"))
(= {:suit :heart :rank 3} (recognize-card "H5"))
(= {:suit :club :rank 12} (recognize-card "CA"))
(= (range 13) (map (comp :rank recognize-card str)
                   '[S2 S3 S4 S5 S6 S7
                     S8 S9 ST SJ SQ SK SA]))
