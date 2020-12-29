;p46: Flipping out
;Write a higher-order function which flips the order of the arguments of an input function
;
(defn flip-args
  "Flips the order of the arguments of an input function."
  [f]
  (fn [arg1 arg2]
    (f arg2 arg1)))

;tests
(= 3 ((flip-args nth) 2 [1 2 3 4 5]))
(= true ((flip-args >) 7 8))
(= 4 ((flip-args quot) 2 8))
(= [1 2 3] ((flip-args take) [1 2 3 4 5] 3))
