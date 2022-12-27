;; p49: Split a sequence

;; Write a function which will split a sequence into two parts.
;; restrictions: split-at

(defn split-seq-at
  [N xs]
  (vector (take N xs) (drop N xs)))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (split-seq-at 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]]))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (split-seq-at 1 [:a :b :c :d]) [[:a] [:b :c :d]]))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (split-seq-at 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]]))))
