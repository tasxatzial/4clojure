;; p27: Palindrome Detector

;; Write a function which returns true if the given sequence is a palindrome

(defn palindrome?
  [xs]
  (= (seq xs) (reverse xs)))

(clojure.test/deftest test1
  (clojure.test/testing
    (clojure.test/is (false? (palindrome? '(1 2 3 4 5))))))

(clojure.test/deftest test2
  (clojure.test/testing
    (clojure.test/is (true? (palindrome? "racecar")))))

(clojure.test/deftest test3
  (clojure.test/testing
    (clojure.test/is (true? (palindrome? [:foo :bar :foo])))))

(clojure.test/deftest test4
  (clojure.test/testing
    (clojure.test/is (true? (palindrome? '(1 1 3 3 1 1))))))

(clojure.test/deftest test5
  (clojure.test/testing
    (clojure.test/is (false? (palindrome? '(:a :b :c))))))
