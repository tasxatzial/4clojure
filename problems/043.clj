;; p43: Reverse Interleave

;; Write a function which reverses the interleave process into N number of subsequences

(defn reverse-interleave
  [xs N]
  (let [partitioned (partition N xs)]
    (->> partitioned
         (apply interleave)
         (partition (count partitioned)))))

(defn reverse-interleave2
  [xs N]
  (apply map list (partition N xs)))

;; testing reverse-interleave -----------------------------------
(clojure.test/deftest test1-reverse-interleave
  (clojure.test/testing
    (clojure.test/is (= (reverse-interleave [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6))))))

(clojure.test/deftest test2-reverse-interleave
  (clojure.test/testing
    (clojure.test/is (= (reverse-interleave (range 9) 3) '((0 3 6) (1 4 7) (2 5 8))))))

(clojure.test/deftest test3-reverse-interleave
  (clojure.test/testing
    (clojure.test/is (= (reverse-interleave (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9))))))

;; testing reverse-interleave2 -----------------------------------
(clojure.test/deftest test1-reverse-interleave2
  (clojure.test/testing
    (clojure.test/is (= (reverse-interleave2 [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6))))))

(clojure.test/deftest test2-reverse-interleave2
  (clojure.test/testing
    (clojure.test/is (= (reverse-interleave2 (range 9) 3) '((0 3 6) (1 4 7) (2 5 8))))))

(clojure.test/deftest test3-reverse-interleave2
  (clojure.test/testing
    (clojure.test/is (= (reverse-interleave2 (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9))))))
