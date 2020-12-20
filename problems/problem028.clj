;p28: Flatten a Sequence
;Write a function which flattens a sequence
;
;restrictions: flatten
;
(defn my-flatten
  "Flattens col."
  [col]
  (reduce (fn [result x]
            (concat result
                    (if (sequential? x)
                      (my-flatten x)
                      (list x))))
          '()
          col))
