;; p66: Greatest Common Divisor

;; Given two integers, write a function which returns the greatest common divisor.

(defn get-cgd
  [N1 N2]
  (if (= 0 N2)
    N1
    (recur N2 (mod N1 N2))))

(deftest tests
  (testing "test1"
    (is (= (get-cgd 2 4) 2)))
  (testing "test2"
    (is (= (get-cgd 10 5) 5)))
  (testing "test3"
    (is (= (get-cgd 5 7) 1)))
  (testing "test4"
    (is (= (get-cgd 1023 858) 33))))
