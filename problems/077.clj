;p77: Anagram Finder
;Write a function which finds all the anagrams in a vector of words. A word x is an anagram of word y if all the
;letters in x can be rearranged in a different order to form y. Your function should return a set of sets, where each
;sub-set is a group of words which are anagrams of each other. Each sub-set should have at least two words. Words
;without any anagrams should not be included in the result

(defn split-word
  "Splits a string into chars (sorted)."
  [s]
  (sort (map identity s)))

(defn remove-single
  "Removes from col all cols that consist of one element."
  [col]
  (set (filter #(> (count %) 1) col)))

(defn collect-anagrams
  "Returns a set of all anagrams of string s that are found in word-map.
  word-map consists of [word (split-word word)]"
  [[s split-s] word-map]
  (reduce (fn [result x]
            (if (= (second x) split-s)
              (conj result (first x))
              result))
          #{}
          word-map))

(defn split-word-map
  "Splits a collection of words into a map, for each word the
  [word (split-word word)] is added to the map."
  [col]
  (let [no-duplicates (set col)]
    (reduce (fn [result x]
              (conj result [x (split-word x)]))
            {}
            no-duplicates)))

(defn dissoc-words
  "Removes all words from word-map."
  [word-map words]
  (reduce (fn [result x]
            (dissoc result x))
          word-map
          words))

(defn group-by-anagrams
  "Groups col by anagrams."
  ([col] (group-by-anagrams #{} (split-word-map col)))
  ([result word-map]
   (if (empty? word-map)
     result
     (let [word (first word-map)
           rest-word-map (dissoc word-map (first word))
           anagrams (collect-anagrams word rest-word-map)
           new-map (dissoc-words rest-word-map anagrams)
           new-result (conj result (conj anagrams (first word)))]
       (recur new-result new-map)))))

(def remove-single-anagrams #(remove-single (group-by-anagrams %)))

;tests
(= (remove-single-anagrams ["meat" "mat" "team" "mate" "eat"])
   #{#{"meat" "team" "mate"}})
(= (remove-single-anagrams ["veer" "lake" "item" "kale" "mite" "ever"])
   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})
