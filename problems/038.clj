;; p38: Maximum value

;; Write a function which takes a variable number of parameters and returns the maximum value
;; restrictions: max, max-key

(defn get-max
  [& xs]
  (reduce (fn [result x]
            (if (> x result)
              x
              result))
          (first xs)
          (rest xs)))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (get-max 1 8 3 4) 8))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (get-max 30 20) 30))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (get-max 45 67 11) 67))))
