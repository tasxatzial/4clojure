;; p21: Nth element

;; Write a function which returns the Nth element from a sequence
;; restrictions: nth

(defn get-nth
  [xs n]
  (if (zero? n)
    (first xs)
    (recur (rest xs) (dec n))))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (get-nth '(4 5 6 7) 2) 6))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (get-nth [:a :b :c] 0) :a))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (get-nth [1 2 3 4] 1) 2))))

(clojure.test/deftest test4
  (clojure.test/testing
    (clojure.test/is (= (get-nth '([1 2] [3 4] [5 6]) 2) [5 6]))))
