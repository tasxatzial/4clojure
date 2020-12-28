;p115: The Balance of N
;A balanced number is one whose component digits have the same sum on the
;left and right halves of the number. Write a function which accepts an
;integer n, and returns true iff n is balanced
;
(defn to-digits
  "Returns a list of the digits of a number."
  [N]
  (map (comp read-string str) (str N)))

(defn count-half-digits
  "Returns how many items from digits are in each of
  the left/right half of digits."
  [digits]
  (let [digit-count (count digits)]
    (if (= 0 (mod digit-count 2))
      (Math/round ^double (* digit-count 0.5))
      (Math/round ^double (* (dec digit-count) 0.5)))))

(defn balanced?
  "Returns true if N is balanced, false otherwise."
  [N]
  (let [digits (to-digits N)
        half-digit-count (count-half-digits digits)
        sum-left-half (apply + (take half-digit-count digits))
        sum-right-half (apply + (take-last half-digit-count digits))]
    (= sum-left-half sum-right-half)))

(= true (balanced? 11))
(= true (balanced? 121))
(= false (balanced? 123))
(= true (balanced? 0))
(= false (balanced? 88099))
(= true (balanced? 89098))
(= true (balanced? 89089))
(= (take 20 (filter balanced? (range)))
   [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])
