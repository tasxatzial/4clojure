;p27: Palindrome Detector
;Write a function which returns true if the given sequence is a palindrome
(def p27 (fn palindrome?
           ([col] (palindrome? col 0))
           ([col n]
            (if (= (count col) n)
              true
              (if (= (nth col (- (count col) 1)) (first col))
                (recur col (+ n 1))
                false)))))

;tests
(map p27 ['(1 2 3 4 5) "racecar" [:foo :bar :foo] '(1 1 3 3 1 1) '(:a :b :c)])