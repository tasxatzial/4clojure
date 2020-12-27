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

(= (my-flatten '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (my-flatten ["a" ["b"] "c"]) '("a" "b" "c"))
(= (my-flatten '((((:a))))) '(:a))
