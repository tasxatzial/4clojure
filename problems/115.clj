;p115: The Balance of N
;A balanced number is one whose component digits have the same sum on the left and right halves of the number.
;Write a function which accepts an integer n, and returns true iff n is balanced
(def p115 (fn [N]
            (let [toseq (memoize (fn [N] (map (comp read-string str) (str N))))
                  half (memoize (fn [N] (Math/round (Math/floor (/ (count (toseq N)) 2)))))]
              (= (apply + (take (half N) (toseq N))) (apply + (drop (if (odd? (count (toseq N)))
                                                                        (inc (half N))
                                                                        (half N))
                                                                      (toseq N)))))))

;tests
(p115 11)
(p115 121)
(p115 123)
(p115 0)
(p115 88099)
(p115 89098)
(p115 89089)
(take 20 (filter p115 (range)))