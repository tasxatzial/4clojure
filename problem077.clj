;p77: Anagram Finder
;Write a function which finds all the anagrams in a vector of words. A word x is an anagram of word y if all the
;letters in x can be rearranged in a different order to form y. Your function should return a set of sets, where each
;sub-set is a group of words which are anagrams of each other. Each sub-set should have at least two words. Words
;without any anagrams should not be included in the result

;solution 1 ----------------------------------------
(def p77 (fn [col]
           (letfn [(p77_3 [col]                             ;collect all strings in every value in a set
                     (reduce (fn [result y]
                               (conj result (reduce (fn [result x]
                                                      (conj result (x 0)))
                                                    #{} y)))
                             #{} col))
                   (p77_2 [col] (filter #(> (count %) 1) col)) ;remove all values that have one element
                   (p77_1 [col] (reduce (fn [result x]      ;get the values in group-by
                                    (conj result (get col x) ))
                                  #{} (keys col)))
                   (p77_0 [col sorted] (group-by #(get % 1) (map (fn [x y] ;group-by the sorted letter list
                                                                   [x y]) col sorted)))
                   (p77_ [col] (map (fn [x]                 ;split into letters, then sort
                                      (sort (clojure.string/split x #""))) col))]
             (p77_3 (p77_2 (p77_1 (p77_0 col (p77_ col))))))))

;solution 1 tests
(p77 ["meat" "mat" "team" "mate" "eat"])
(p77 ["veer" "lake" "item" "kale" "mite" "ever"])

;solution 2 ----------------------------------------
(def p77_2 (fn [col]
             (letfn [(F [string] (sort (clojure.string/split string #"")))
                     (Remove-single [col] (set (filter #(> (count %) 1) col)))
                     (Process-col [col] (if (empty? col)
                                          #{}
                                          (reduce (fn [result x]
                                                    (if (= (F x) (F (first col)))
                                                      (conj result x)
                                                      result))
                                                  #{(first col)} (next col))))]
               (Remove-single ((fn G [result col]
                                 (if (empty? col)
                                   result
                                   (if (not (some true? (map #(contains? % (first col)) result)))
                                     (G (conj result (Process-col col)) (next col))
                                     (G result (next col)))))
                               #{} col)))))

;solution 2 tests
(p77_2 ["meat" "mat" "team" "mate" "eat"])
(p77_2 ["veer" "lake" "item" "kale" "mite" "ever"])