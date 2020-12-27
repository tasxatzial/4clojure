;p74: Filter Perfect Squares
;Given a string of comma separated integers, write a function which returns a new comma
;separated string that only contains the numbers which are perfect squares
;
(defn square?
  "Returns true if x is square, false otherwise."
  [x]
  (let [sqrt (Math/sqrt x)
        rounded-sqrt (Math/round sqrt)
        diff (Math/abs (- rounded-sqrt sqrt))]
    (< diff (Math/ulp diff))))

(defn filter-squares
  "Given a string s of comma separated integers, it returns a new comma separated string
  that only contains the numbers which are perfect squares."
  [s]
  (let [split-s (clojure.string/split s #",")
        ints (map #(Integer. ^String %) split-s)]
    (clojure.string/join "," (filter square? ints))))

(= (filter-squares "4,5,6,7,8,9") "4,9")
(= (filter-squares "15,16,25,36,37") "16,25,36")
