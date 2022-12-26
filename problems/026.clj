;; p26: Fibonacci Sequence

;; Write a function which returns the first X fibonacci numbers

(defn get-fib
  [N]
  (letfn [(lazy-fib
            ([]
             (lazy-fib 1 1))
            ([prev next]
             (lazy-seq (cons prev (lazy-fib next (+ prev next))))))]
    (take N (lazy-fib))))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (get-fib 3) '(1 1 2)))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (get-fib 6) '(1 1 2 3 5 8)))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (get-fib 8) '(1 1 2 3 5 8 13 21)))))
