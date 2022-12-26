;; p33: Replicate a Sequence

;; Write a function which replicates each element of a sequence a variable number of times

(defn replicate-elements
  [xs N]
  (reduce into [] (map #(take N (repeat %)) xs)))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (replicate-elements [1 2 3] 2) '(1 1 2 2 3 3)))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (replicate-elements [:a :b] 4) '(:a :a :a :a :b :b :b :b)))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (replicate-elements [4 5 6] 1) '(4 5 6)))))

(clojure.test/deftest test4
  (clojure.test/testing
    (clojure.test/is (= (replicate-elements [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4])))))

(clojure.test/deftest test5
  (clojure.test/testing
    (clojure.test/is (= (replicate-elements [44 33] 2) [44 44 33 33]))))
