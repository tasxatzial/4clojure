;p108: Lazy Searching
;Given any number of sequences, each sorted from smallest to largest, find the
;smallest single number which appears in all of the sequences. The sequences may
;be infinite, so be careful to search lazily
;
(defn my-contains?
  "Returns true if col contains X, false otherwise. col must be sorted in
  increasing order and may be infinite."
  [col X]
  (= X (last (take-while #(<= % X) col))))

(defn _smallest-common
  "Returns the smallest number which appears in all args. Every collection
   in args must be sorted in increasing order."
  ([args] (_smallest-common (first args) (rest args)))
  ([first-arg rest-args]
   (if (empty? first-arg)
     nil
     (let [num (first first-arg)]
       (if (some false? (map #(my-contains? % num) rest-args))
         (_smallest-common (rest first-arg) rest-args)
         num)))))

(defn smallest-common
  "Returns the smallest number which appears in all args. Every collection
   in args must be sorted in increasing order."
  [& args]
  (_smallest-common args))

(= 3 (smallest-common [3 4 5]))
(= 4 (smallest-common [1 2 3 4 5 6 7] [0.5 3/2 4 19]))
(= 7 (smallest-common (range) (range 0 100 7/6) [2 3 5 7 11 13]))
(= 64 (smallest-common (map #(* % % %) (range)) ;; perfect cubes
                       (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
                       (iterate inc 20))) ;; at least as large as 20
