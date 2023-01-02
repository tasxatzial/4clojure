;; p74: Filter Perfect Squares

;; Given a string of comma separated integers, write a function which returns a new
;; comma separated string that only contains the numbers which are perfect squares

(ns p74.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn square?
  "Returns true if x is square, false otherwise."
  [x]
  (let [sqrt (Math/sqrt x)
        rounded-sqrt (Math/round sqrt)
        diff (Math/abs (- rounded-sqrt sqrt))]
    (< diff (Math/ulp diff))))

(defn get-squares-str
  [s]
  (let [ints (read-string (str "[" s "]"))
        squares (filter square? ints)]
    (clojure.string/join "," squares)))

(deftest tests
  (testing "test1"
    (is (= (get-squares-str "4,5,6,7,8,9") "4,9")))
  (testing "test2"
    (is (= (get-squares-str "15,16,25,36,37") "16,25,36"))))
