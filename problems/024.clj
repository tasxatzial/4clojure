;; p24: Sum It All Up

;; Write a function which returns the sum of a sequence of numbers

(defn my-sum
  [xs]
  (apply + xs))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (my-sum [1 2 3]) 6))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (my-sum (list 0 -2 5 5)) 8))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (my-sum #{4 2 1}) 7))))

(clojure.test/deftest test4
  (clojure.test/testing
    (clojure.test/is (= (my-sum '(0 0 -1)) -1))))

(clojure.test/deftest test5
  (clojure.test/testing
    (clojure.test/is (= (my-sum '(1 10 3)) 14))))
