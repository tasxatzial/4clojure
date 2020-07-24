;p74: Filter Perfect Squares
;Given a string of comma separated integers, write a function which returns a new comma separated string that only
;contains the numbers which are perfect squares
(def p74 (fn [string]
           (clojure.string/join "," (filter #(< (- (Math/sqrt %) (Math/floor (Math/sqrt %))) 0.001)
                                            (map #(read-string %)
                                                 (clojure.string/split string #","))))))

;tests
(p74 "4,5,6,7,8,9")
(p74 "15,16,25,36,37")