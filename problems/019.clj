;; p19: Last element

;; Write a function which returns the last element in a sequence.
;; restrictions: last

(defn get-last
  [xs]
  (when (seq xs)
    (nth xs (dec (count xs)))))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= (get-last [1 2 3 4 5]) 5))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= (get-last '(5 4 3)) 3))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= (get-last ["b" "c" "d"]) "d"))))
