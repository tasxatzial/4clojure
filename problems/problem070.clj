;p70: Word Sorting
;Write a function that splits a sentence up into a sorted list of words. Capitalization should not affect sort
;order and punctuation should be ignored
(def p70 (fn [string1]
           (sort-by clojure.string/lower-case
                    (clojure.string/split (clojure.string/replace string1 #"[^A-Za-z ]" "") #" "))))

;tests
(p70 "Have a nice day.")
(p70 "Clojure is a fun language!")
(p70 "Fools fall for foolish follies.")