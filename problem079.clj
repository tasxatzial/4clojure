;p79: Triangle Minimal Path
;Write a function which calculates the sum of the minimal path through a triangle. The triangle is represented as a
;collection of vectors. The path should start at the top of the triangle and move to an adjacent number on the next
;row until the bottom of the triangle is reached
(def p79 (fn min-path
           ([col] (min-path col 0 0))
           ([col vidx idx]
            (if (= (- (count col) 1) vidx)
              ((nth col vidx) idx)
              (let [x (min-path col (+ 1 vidx) idx)
                    y (min-path col (+ 1 vidx) (+ 1 idx))]
                (+ (min x y) ((nth col vidx) idx)))))))

;tests
(p79 '([1]
       [2 4]
       [5 1 4]
       [2 3 4 5]))
(p79 '([3]
       [2 4]
       [1 9 3]
       [9 9 2 4]
       [4 6 6 7 8]
       [5 7 3 5 1 4]))