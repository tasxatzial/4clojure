;; p39: Interleave Two Seqs

;; Write a function which takes two sequences and returns the first item from each, then the second item from each, then the third, etc
;; restrictions: interleave

(defn interleave-seq
  [xs1 xs2]
  (reduce into [] (map #(vector %1 %2) xs1 xs2)))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (interleave-seq [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c)))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (interleave-seq [1 2] [3 4 5 6]) '(1 3 2 4)))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (interleave-seq [1 2 3 4] [5]) [1 5]))))
