;; p30: Compress a Sequence

;; Write a function which removes consecutive duplicates from a sequence

(defn compress-seq
  ([xs]
   (if (empty? xs)
     []
     (compress-seq (map identity xs) [])))
  ([[x & rest-xs] result]
   (if (seq rest-xs)
     (if (= x (first rest-xs))
       (recur rest-xs result)
       (recur rest-xs (conj result x)))
     (conj result x))))

(defn compress-seq2
  [xs]
  (map first (partition-by identity xs)))

;; testing compress-seq -----------------------------------
(clojure.test/deftest test1-compress-seq
  (clojure.test/testing
    (clojure.test/is (= (apply str (compress-seq "Leeeeeerrroyyy")) "Leroy"))))

(clojure.test/deftest test2-compress-seq
  (clojure.test/testing
    (clojure.test/is (= (compress-seq [1 1 2 3 3 2 2 3]) '(1 2 3 2 3)))))

(clojure.test/deftest test3-compress-seq
  (clojure.test/testing
    (clojure.test/is (= (compress-seq [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))))

;; testing compress-seq2 -----------------------------------
(clojure.test/deftest test1-compress-seq2
  (clojure.test/testing
    (clojure.test/is (= (apply str (compress-seq2 "Leeeeeerrroyyy")) "Leroy"))))

(clojure.test/deftest test2-compress-seq2
  (clojure.test/testing
    (clojure.test/is (= (compress-seq2 [1 1 2 3 3 2 2 3]) '(1 2 3 2 3)))))

(clojure.test/deftest test3-compress-seq2
  (clojure.test/testing
    (clojure.test/is (= (compress-seq2 [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))))
