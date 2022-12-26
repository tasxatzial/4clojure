;; p32: Duplicate a Sequence

;; Write a function which duplicates each element of a sequence

(defn duplicate-elements
  [xs]
  (reduce into [] (map #(vector % %) xs)))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (duplicate-elements [1 2 3]) '(1 1 2 2 3 3)))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (duplicate-elements [:a :a :b :b]) '(:a :a :a :a :b :b :b :b)))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (duplicate-elements [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])))))

(clojure.test/deftest test4
  (clojure.test/testing
    (clojure.test/is (= (duplicate-elements [44 33]) [44 44 33 33]))))
