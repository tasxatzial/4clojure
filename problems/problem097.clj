;p97: Pascal's Triangle
;Pascal's triangle is a triangle of numbers computed using the following rules:
;A. The first row is 1.
;B. Each successive row is computed by adding together adjacent numbers in the row above,
;   and adding a 1 to the beginning and end of the row.
;
;Write a function which returns the nth row of Pascal's Triangle
;
(defn next-row
  "Given a row, it returns the next row of pascal's triangle."
  [row]
  (let [zero-append-row (conj row 0)
        zero-prepend-row (into [0] row)]
    (apply vector (map + zero-append-row zero-prepend-row))))

(defn pascal-triangle
  "Returns the Nth row of pascal's triangle."
  ([N]
   (case N
     1 [1]
     2 [1 1]
     (pascal-triangle (dec N) (next-row [1 1]))))
  ([N row]
   (if (= N 2)
     row
     (recur (dec N) (next-row row)))))

(= (pascal-triangle 1) [1])
(= (map pascal-triangle (range 1 6))
   [[1]
    [1 1]
    [1 2 1]
    [1 3 3 1]
    [1 4 6 4 1]])
(= (pascal-triangle 11)
   [1 10 45 120 210 252 210 120 45 10 1])
