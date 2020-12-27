;p122: Read a binary number
;Convert a binary number, provided in the form of a string, to its numerical value

;solution 1 ----------------------------------------
(def p122 (fn [N]
             (let [c (count N)]
               ((fn [idx result]
                  (if (= idx c)
                    result
                    (recur (+ idx 1) (+ result (* (Character/digit (get N idx) 10) (Math/round (Math/pow 2 (- c idx 1))))))))
                0 0))))

;solution 1 tests
(p122 "0")
(p122 "111")
(p122 "1000")
(p122 "1001")
(p122 "11111111")
(p122 "10101010101")
(p122 "1111111111111111")

;solution 2 ----------------------------------------
(def p122_2 (fn [N]
              (reduce + (map #(* %1 (Math/round (Math/pow 2 %2)))
                             (map #(Character/digit % 10) N) (range (- (count N) 1) -1 -1)))))

;solution 2 tests
(p122_2 "0")
(p122_2 "111")
(p122_2 "1000")
(p122_2 "1001")
(p122_2 "11111111")
(p122_2 "10101010101")
(p122_2 "1111111111111111")