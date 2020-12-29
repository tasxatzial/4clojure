;p171: Intervals
;Write a function that takes a sequence of integers and returns a sequence of "intervals".
;Each interval is a a vector of two integers, start and end, such that all integers between
;start and end (inclusive) are contained in the input sequence
;
(defn find-intervals
  "Takes a sequence of integers col and returns a sequence of \"intervals\".
   Each interval is represented by two adjacent numbers. For example in
   [1 3 4 5] the intervals are 1-3 and 4-5."
  ([col]
   (if (empty? col)
     []
     (let [sorted-col (sort col)]
       (find-intervals sorted-col [(first sorted-col)] (first sorted-col)))))
  ([col result prev-num]
   (if (empty? col)
     (conj result prev-num)
     (let [cur-num (first col)]
       (if (> cur-num (inc prev-num))
         (recur (rest col) (conj result prev-num cur-num) cur-num)
         (recur (rest col) result cur-num))))))

(defn interval-split
  "Takes a sequence of integers col and returns a sequence of \"intervals\"."
  [col]
  (let [intervals (partition 2 (find-intervals col))]
    (map #(apply vector %) intervals)))

;tests
(= (interval-split [1 2 3]) [[1 3]])
(= (interval-split [10 9 8 1 2 3]) [[1 3] [8 10]])
(= (interval-split [1 1 1 1 1 1 1]) [[1 1]])
(= (interval-split []) [])
(= (interval-split [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11])
   [[1 4] [6 6] [9 11] [13 17] [19 19]])
