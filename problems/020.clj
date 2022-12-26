;; p20: Penultimate element

;; Write a function which returns the second to last element from a sequence.

(defn get-second-to-last
  [xs]
  (let [c (count xs)]
    (when (> c 1)
      (nth xs (- c 2)))))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (get-second-to-last (list 1 2 3 4 5)) 4))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (get-second-to-last ["a" "b" "c"]) "b"))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (get-second-to-last [[1 2] [3 4]]) [1 2]))))
