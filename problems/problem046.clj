;p46: Flipping out
;Write a higher-order function which flips the order of the arguments of an input function
;
(defn flip-args
  "Flips the order of the arguments of an input function."
  [f]
  (fn [arg1 arg2]
    (f arg2 arg1)))
