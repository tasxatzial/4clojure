;; p31: Pack a Sequence

;; Write a function which packs consecutive duplicates into sub-lists

(defn partition-by-identity
  ([xs]
   (if (empty? xs)
     []
     (partition-by-identity xs 1 [])))
  ([[x & rest-xs] equal-count result]
   (if (seq rest-xs)
     (if (= x (first rest-xs))
       (recur rest-xs (inc equal-count) result)
       (recur rest-xs 1 (conj result (take equal-count (repeat x)))))
     (conj result (take equal-count (repeat x))))))

(defn partition-by-identity2
  [xs]
  (partition-by identity xs))

;; testing partition-by-identity -----------------------------------
(clojure.test/deftest test1-partition-by-identity
  (clojure.test/testing
    (clojure.test/is (= (partition-by-identity [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3))))))

(clojure.test/deftest test2-partition-by-identity
  (clojure.test/testing
    (clojure.test/is (= (partition-by-identity [:a :a :b :b :c]) '((:a :a) (:b :b) (:c))))))

(clojure.test/deftest test3-partition-by-identity
  (clojure.test/testing
    (clojure.test/is (= (partition-by-identity [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4]))))))

;; testing partition-by-identity2 -----------------------------------
(clojure.test/deftest test1-partition-by-identity2
  (clojure.test/testing
    (clojure.test/is (= (partition-by-identity2 [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3))))))

(clojure.test/deftest test2-partition-by-identity2
  (clojure.test/testing
    (clojure.test/is (= (partition-by-identity2 [:a :a :b :b :c]) '((:a :a) (:b :b) (:c))))))

(clojure.test/deftest test3-partition-by-identity2
  (clojure.test/testing
    (clojure.test/is (= (partition-by-identity2 [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4]))))))
