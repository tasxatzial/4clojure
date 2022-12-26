;; p23: Reverse a Sequence

;; Write a function which reverses a sequence
;; restrictions: reverse, rseq

(defn reverse-seq
  [xs]
  (into '() xs))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (reverse-seq [1 2 3 4 5]) [5 4 3 2 1]))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (reverse-seq (sorted-set 5 7 2 7)) '(7 5 2)))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (reverse-seq [[1 2] [3 4] [5 6]]) [[5 6] [3 4] [1 2]]))))
