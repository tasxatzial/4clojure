;; p25: Find the odd numbers

;; Write a function which returns only the odd numbers from a sequence

(defn find-odd
  [xs]
  (filter odd? xs))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (find-odd #{1 2 3 4 5}) '(1 3 5)))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (find-odd [4 2 1 6]) '(1)))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (find-odd [2 2 4 6]) '()))))

(clojure.test/deftest test4
  (clojure.test/testing
    (clojure.test/is (= (find-odd [1 1 1 3]) '(1 1 1 3)))))
