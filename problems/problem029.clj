;p29: Get the Caps
;Write a function which takes a string and returns a new string containing only the capital letters
;
;solution 1
(defn get-caps1
  "Returns a new string containing only the capital letters."
  [s]
  (apply str (filter #(and (>= (int %) (int \A)) (<= (int %) (int \Z))) s)))

;solution 2
(defn get-caps2
  "Returns a new string containing only the capital letters."
  [s]
  (apply str (re-seq #"[A-Z]" s)))
