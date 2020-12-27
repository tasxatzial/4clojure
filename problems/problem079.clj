;p79: Triangle Minimal Path
;Write a function which calculates the sum of the minimal path through a triangle. The triangle is represented as a
;collection of vectors. The path should start at the top of the triangle and move to an adjacent number on the next
;row until the bottom of the triangle is reached
;
(defn min-path-sum
  "Calculates the sum of the minimal path through a triangle."
  ([col] (min-path-sum col 0 0))
  ([col outer-idx inner-idx]
   (if (= (dec (count col)) outer-idx)
     ((nth col outer-idx) inner-idx)
     (let [x (min-path-sum col (inc outer-idx) inner-idx)
           y (min-path-sum col (inc outer-idx) (inc inner-idx))]
       (+ (min x y) ((nth col outer-idx) inner-idx))))))

(= 7 (min-path-sum '([1]
                     [2 4]
                     [5 1 4]
                     [2 3 4 5]))) ; 1->2->1->3

(= 20 (min-path-sum '([3]
                      [2 4]
                      [1 9 3]
                      [9 9 2 4]
                      [4 6 6 7 8]
                      [5 7 3 5 1 4]))) ; 3->4->3->2->7->1
