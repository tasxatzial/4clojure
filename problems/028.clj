;; p28: Flatten a Sequence

;; Write a function which flattens a sequence
;; restrictions: flatten

(defn make-flat
  [xs]
  (reduce (fn flat- [result x]
            (if (sequential? x)
              (reduce flat- result x)
              (conj result x)))
          []
          xs))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (make-flat '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6)))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (make-flat ["a" ["b"] "c"]) '("a" "b" "c")))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (make-flat '((((:a))))) '(:a)))))
