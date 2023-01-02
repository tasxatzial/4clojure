;; p63: Group a Sequence

;; Given a function f and a sequence s, write a function which returns a map.
;; The keys should be the values of f applied to each item in s.
;; The value at each key should be a vector of corresponding items in the order
;; they appear in s.
;; restrictions: group-by

(ns p63.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn group-seq-by
  [f xs]
  (reduce (fn [result x]
            (let [fx (f x)]
              (if (contains? result fx)
                (update result fx conj x)
                (assoc result fx [x]))))
          {}
          xs))

(deftest tests
  (testing "test1"
    (is (= (group-seq-by #(> % 5) [1 3 6 8])
           {false [1 3], true [6 8]})))
  (testing "test2"
    (is (= (group-seq-by #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
           {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})))
  (testing "test3"
    (is (= (group-seq-by count [[1] [1 2] [3] [1 2 3] [2 3]])
           {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]}))))
