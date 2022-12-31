;; p62: Re-implement Iterate

;; Given a side-effect free function f and an initial value x write a function which
;; returns an infinite lazy sequence of x, (f x), (f (f x)), (f (f (f x))), etc.
;; restrictions: iterate

(defn iterate-fn
  [f val]
  (lazy-seq
    (cons val (iterate-fn f (f val)))))

(deftest tests
  (testing "test1"
    (is (= (take 5 (iterate-fn #(* 2 %) 1)) [1 2 4 8 16])))
  (testing "test2"
    (is (= (take 100 (iterate-fn inc 0)) (take 100 (range)))))
  (testing "test3"
    (is (= (take 9 (iterate-fn #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3]))))))
