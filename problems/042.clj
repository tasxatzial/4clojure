;; p42: Factorial Fun

;; Write a function which calculates factorials

(defn factorial
  [N]
  (reduce * (range 1 (inc N))))

(defn factorial2
  ([N]
   (if (zero? N)
     1
     (factorial2 N 1)))
  ([N result]
   (if (= N 1)
     result
     (recur (dec N) (* result N)))))

;; testing factorial -----------------------------------
(clojure.test/deftest test1-factorial
  (clojure.test/testing
    (clojure.test/is (= (factorial 1) 1))))

(clojure.test/deftest test2-factorial
  (clojure.test/testing
    (clojure.test/is (= (factorial 3) 6))))

(clojure.test/deftest test3-factorial
  (clojure.test/testing
    (clojure.test/is (= (factorial 5) 120))))

(clojure.test/deftest test4-factorial
  (clojure.test/testing
    (clojure.test/is (= (factorial 8) 40320))))

;; testing factorial2 -----------------------------------
(clojure.test/deftest test1-factorial2
  (clojure.test/testing
    (clojure.test/is (= (factorial2 1) 1))))

(clojure.test/deftest test2-factorial2
  (clojure.test/testing
    (clojure.test/is (= (factorial2 3) 6))))

(clojure.test/deftest test3-factorial3
  (clojure.test/testing
    (clojure.test/is (= (factorial2 5) 120))))

(clojure.test/deftest test4-factorial4
  (clojure.test/testing
    (clojure.test/is (= (factorial2 8) 40320))))
