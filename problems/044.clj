;; p44: Rotate Sequence

;; Write a function which can rotate a sequence in either direction

(defn rotate
  [N xs]
  (let [posN (mod N (count xs))]
    (concat (drop posN xs) (take posN xs))))

(defn rotate2
  [N xs]
  (let [posN (mod N (count xs))
        split-xs (split-at posN xs)]
    (concat (second split-xs) (first split-xs))))

;; testing rotate -----------------------------------
(clojure.test/deftest test1-rotate
  (clojure.test/testing
    (clojure.test/is (= (rotate 2 [1 2 3 4 5]) '(3 4 5 1 2)))))

(clojure.test/deftest test2-rotate
  (clojure.test/testing
    (clojure.test/is (= (rotate -2 [1 2 3 4 5]) '(4 5 1 2 3)))))

(clojure.test/deftest test3-rotate
  (clojure.test/testing
    (clojure.test/is (= (rotate 6 [1 2 3 4 5]) '(2 3 4 5 1)))))

(clojure.test/deftest test4-rotate
  (clojure.test/testing
    (clojure.test/is (= (rotate -4 '(:a :b :c)) '(:c :a :b)))))

;; testing rotate2 -----------------------------------
(clojure.test/deftest test1-rotate2
  (clojure.test/testing
    (clojure.test/is (= (rotate2 2 [1 2 3 4 5]) '(3 4 5 1 2)))))

(clojure.test/deftest test2-rotate2
  (clojure.test/testing
    (clojure.test/is (= (rotate2 -2 [1 2 3 4 5]) '(4 5 1 2 3)))))

(clojure.test/deftest test3-rotate2
  (clojure.test/testing
    (clojure.test/is (= (rotate2 6 [1 2 3 4 5]) '(2 3 4 5 1)))))

(clojure.test/deftest test4-rotate2
  (clojure.test/testing
    (clojure.test/is (= (rotate2 -4 '(:a :b :c)) '(:c :a :b)))))
