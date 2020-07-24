;p108: Lazy Searching
;Given any number of sequences, each sorted from smallest to largest, find the smallest single number which appears
;in all of the sequences. The sequences may be infinite, so be careful to search lazily
(def p108 (fn [& args]
            (letfn [(p108_S [col X]
                      (= X (last (take-while #(<= % X) col))))]
              ((fn my-lz [N]
                 (if (nil? (nth (first args) N nil))
                   nil
                   (if (some #(false? %) (map p108_S (rest args) (repeat (nth (first args) N))))
                     (my-lz (+ N 1))
                     (nth (first args) N))))
               0))))

;tests
(p108 [1 2 3 4 5 6 7] [0.5 3/2 4 19])
(p108 (range) (range 0 100 7/6) [2 3 5 7 11 13])
(p108 (map #(* % % %) (range)) ;; perfect cubes
    (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
    (iterate inc 20))    ;64