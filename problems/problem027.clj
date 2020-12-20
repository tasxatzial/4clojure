;p27: Palindrome Detector
;Write a function which returns true if the given sequence is a palindrome
;
(defn is-palindrome?
  "Returns true if col is palindrome, false otherwise."
  [col]
  (let [reversed (reverse col)]
    (not (some true? (map #(not= %1 %2) col reversed)))))
