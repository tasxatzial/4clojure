;p70: Word Sorting
;Write a function that splits a sentence up into a sorted list of words. Capitalization should not affect sort
;order and punctuation should be ignored
;
(defn word-split
  "Splits a sentence up into a sorted list of words."
  [s]
  (let [no-punctuation (clojure.string/replace s #"[^A-Za-z ]" "")]
    (sort-by clojure.string/lower-case (clojure.string/split no-punctuation #" "))))

(= (word-split  "Have a nice day.")
   ["a" "day" "Have" "nice"])
(= (word-split  "Clojure is a fun language!")
   ["a" "Clojure" "fun" "is" "language"])
(= (word-split  "Fools fall for foolish follies.")
   ["fall" "follies" "foolish" "Fools" "for"])
