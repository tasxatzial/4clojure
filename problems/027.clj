;p27: Palindrome Detector
;Write a function which returns true if the given sequence is a palindrome
;
(defn palindrome?
  "Returns true if col is palindrome, false otherwise."
  [col]
  (let [reversed (reverse col)]
    (not (some true? (map #(not= %1 %2) col reversed)))))

;tests
(false? (palindrome? '(1 2 3 4 5)))
(true? (palindrome? "racecar"))
(true? (palindrome? [:foo :bar :foo]))
(true? (palindrome? '(1 1 3 3 1 1)))
(false? (palindrome? '(:a :b :c)))
