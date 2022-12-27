;; p46: Flipping out

;; Write a higher-order function which flips the order of the arguments of an input function

(defn flip-args
  [f]
  (fn [arg1 arg2]
    (f arg2 arg1)))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (= 3 ((flip-args nth) 2 [1 2 3 4 5])))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (= true ((flip-args >) 7 8)))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (= 4 ((flip-args quot) 2 8)))))

(clojure.test/deftest test4
  (clojure.test/testing
    (clojure.test/is (= [1 2 3] ((flip-args take) [1 2 3 4 5] 3)))))
