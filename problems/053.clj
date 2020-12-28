;p53: Longest Increasing Sub-Seq
;Given a vector of integers, find the longest consecutive sub-sequence of increasing numbers.
;If two sub-sequences have the same length, use the one that occurs first.
;An increasing sub-sequence must have a length of 2 or greater to qualify
;
(defn split-increasing
   "Splits col into increasing sub sequences."
   [col]
   (reduce (fn [result x]
             (if (empty? result)
               (conj result [x])
               (if (> x (last (last result)))
                 (let [butlast-result (apply vector (butlast result))
                       last-result (conj (apply vector (last result)) x)]
                   (conj butlast-result last-result))
                 (conj result [x]))))
           []
           col))

(defn longest-increasing
  "Returns the longest increasing sub sequence."
  [col]
  (let [split-col (filter #(> (count %) 1) (split-increasing col))]
    (reduce (fn [result x]
              (if (> (count x) (count result))
                x
                result))
            []
            split-col)))

(= (longest-increasing [1 0 1 2 3 0 4 5]) [0 1 2 3])
(= (longest-increasing [5 6 1 3 2 7]) [5 6])
(= (longest-increasing [2 3 3 4 5]) [3 4 5])
(= (longest-increasing [7 6 5 4]) [])