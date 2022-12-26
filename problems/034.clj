;; p34: Implement range

;; Write a function which creates a list of all integers in a given range
;; restrictions: range

(defn get-range
  [n1 n2]
  (lazy-seq (when (< n1 n2)
              (cons n1 (get-range (inc n1) n2)))))

(defn get-range2
  [n1 n2]
  (loop [result []
         n1 n1]
    (if (< n1 n2)
      (recur (conj result n1) (inc n1))
      result)))

;; testing get-range -----------------------------------
(clojure.test/deftest test1-get-range
  (clojure.test/testing
    (clojure.test/is (= (get-range 1 4) '(1 2 3)))))

(clojure.test/deftest test2-get-range
  (clojure.test/testing
    (clojure.test/is (= (get-range -2 2) '(-2 -1 0 1)))))

(clojure.test/deftest test3-get-range
  (clojure.test/testing
    (clojure.test/is (= (get-range 5 8) '(5 6 7)))))

;; testing get-range2 -----------------------------------
(clojure.test/deftest test1-get-range2
  (clojure.test/testing
    (clojure.test/is (= (get-range2 1 4) '(1 2 3)))))

(clojure.test/deftest test2-get-range2
  (clojure.test/testing
    (clojure.test/is (= (get-range2 -2 2) '(-2 -1 0 1)))))

(clojure.test/deftest test3-get-range2
  (clojure.test/testing
    (clojure.test/is (= (get-range2 5 8) '(5 6 7)))))
