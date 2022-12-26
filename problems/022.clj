;; p22: Count a Sequence

;; Write a function which returns the total number of elements in a sequence
;; restrictions: count

(defn get-count
  [xs]
  (reduce (fn [res _]
            (inc res))
          0
          xs))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (get-count '(1 2 3 3 1)) 5))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (get-count "Hello World") 11))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (get-count [[1 2] [3 4] [5 6]]) 3))))

(clojure.test/deftest test4
  (clojure.test/testing
    (clojure.test/is (= (get-count '(13)) 1))))

(clojure.test/deftest test5
  (clojure.test/testing
    (clojure.test/is (= (get-count '(:a :b :c)) 3))))
