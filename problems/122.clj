;p122: Read a binary number
;Convert a binary number, provided in the form of a string, to its numerical value

(defn to-decimal
  "Coverts a binary number represented as a string, to decimal."
  ([digits] (to-decimal digits 0 (dec (count digits))))
  ([digits result i]
   (if (empty? digits)
     result
     (let [digit (first digits)]
       (if (= \0 digit)
         (recur (rest digits) result (dec i))
         (let [new-result (+ result (Math/round (Math/pow 2 i)))]
           (recur (rest digits) new-result (dec i))))))))

;tests
(= 0     (to-decimal "0"))
(= 7     (to-decimal "111"))
(= 8     (to-decimal "1000"))
(= 9     (to-decimal "1001"))
(= 255   (to-decimal "11111111"))
(= 1365  (to-decimal "10101010101"))
(= 65535 (to-decimal "1111111111111111"))
