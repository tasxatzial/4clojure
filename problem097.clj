;p97: Pascal's Triangle
;Pascal's triangle is a triangle of numbers computed using the following rules:
;A. The first row is 1.
;B. Each successive row is computed by adding together adjacent numbers in the row above, and adding a 1 to the beginning and end of the row.
;Write a function which returns the nth row of Pascal's Triangle
(def p97 (fn [N]
           (if (= 1 N)
             [1]
             (if (= 2 N)
               [1 1]
               ((fn pascal [N row]
                  (if (= N 0)
                    row
                    (letfn [(new-row [N1 result]
                              (if (= N1 (count row))
                                result
                                (recur (+ N1 1) (conj result (+ (get row (- N1 1)) (get row N1))))))]
                      (recur (- N 1) (into [1] (conj (new-row 1 []) 1))))))
                (- N 2) [1 1])))))

;tests
(p97 1)
(map p97 (range 1 6))
(p97 11)