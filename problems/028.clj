;; p28: Flatten a Sequence

;; Write a function which flattens a sequence
;; restrictions: flatten

(defn make-flat
  [xs]
  (reduce (fn [result x]
            (concat result (if (sequential? x)
                             (make-flat x)
                             (list x))))
          '()
          xs))

(defn make-flat2
  [xs]
  (reduce (fn flat- [result x]
            (if (sequential? x)
              (reduce flat- result x)
              (conj result x)))
          []
          xs))

;; testing make-flat -----------------------------------
(clojure.test/deftest test1-make-flat
  (clojure.test/testing
    (clojure.test/is (= (make-flat '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6)))))

(clojure.test/deftest test2-make-flat
  (clojure.test/testing
    (clojure.test/is (= (make-flat ["a" ["b"] "c"]) '("a" "b" "c")))))

(clojure.test/deftest test3-make-flat
  (clojure.test/testing
    (clojure.test/is (= (make-flat '((((:a))))) '(:a)))))

;; testing make-flat2 -----------------------------------
(clojure.test/deftest test1-make-flat2
  (clojure.test/testing
    (clojure.test/is (= (make-flat2 '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6)))))

(clojure.test/deftest test2-make-flat2
  (clojure.test/testing
    (clojure.test/is (= (make-flat2 ["a" ["b"] "c"]) '("a" "b" "c")))))

(clojure.test/deftest test3-make-flat2
  (clojure.test/testing
    (clojure.test/is (= (make-flat2 '((((:a))))) '(:a)))))
