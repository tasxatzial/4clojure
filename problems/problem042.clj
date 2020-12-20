;p42: Factorial Fun
;Write a function which calculates factorials

;solution 1 ----------------------------------------
(def p42 (fn [N]
           ((fn my-factorial [N result]
              (if (= N 0)
                result
                (recur (- N 1) (* result N))))
            N 1)))

;solution 1 tests
(map p42 [1 3 5 8])

;solution 2 ----------------------------------------
(def p42_2 (fn my-recursion [N]
             (if (= N 0)
               1
               (* N (my-recursion (- N 1))))))

;solution 2 tests
(map p42_2 [1 3 5 8])

;solution 3 ----------------------------------------
(def p42_3 #(last (last (take (inc %) (iterate (fn [[x y]]
                                                 [(inc x) (* x y)]) [1 1])))))

;solution 3 tests
(map p42_3 [1 3 5 8])

;solution 4 ----------------------------------------
(def p42_4 (fn [N]
             (reduce * (range 1 (inc N)))))

;solution 4 tests
(map p42_4 [1 3 5 8])