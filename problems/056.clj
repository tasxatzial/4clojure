;; p56: Find Distinct Items

;; Write a function which removes the duplicates from a sequence. Order of the items
;; must be maintained.
;; restrictions: distinct

(ns p56.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn remove-duplicates
  [xs]
  (loop [xs xs
         set-xs #{}
         result []]
    (if (empty? xs)
      result
      (let [[x & rest-xs] xs]
        (if (contains? set-xs x)
          (recur rest-xs set-xs result)
          (let [new-set-xs (conj set-xs x)
                new-result (conj result x)]
            (recur rest-xs new-set-xs new-result)))))))

;; lazy
(defn remove-duplicates2
  [xs]
  (letfn [(_remove-duplicates [xs set-xs]
            (lazy-seq
              (when (seq xs)
                (let [[x & rest-xs] xs]
                  (if (contains? set-xs x)
                    (_remove-duplicates rest-xs set-xs)
                    (cons x (_remove-duplicates rest-xs (conj set-xs x))))))))]
    (_remove-duplicates xs #{})))

(deftest tests-remove-duplicates
  (testing "test1"
    (is (= (remove-duplicates [1 2 1 3 1 2 4]) [1 2 3 4])))
  (testing "test2"
    (is (= (remove-duplicates [:a :a :b :b :c :c]) [:a :b :c])))
  (testing "test3"
    (is (= (remove-duplicates '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))))
  (testing "test4"
    (is (= (remove-duplicates (range 50)) (range 50)))))

(deftest tests-remove-duplicates2
  (testing "test1"
    (is (= (remove-duplicates2 [1 2 1 3 1 2 4]) [1 2 3 4])))
  (testing "test2"
    (is (= (remove-duplicates2 [:a :a :b :b :c :c]) [:a :b :c])))
  (testing "test3"
    (is (= (remove-duplicates2 '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))))
  (testing "test4"
    (is (= (remove-duplicates2 (range 50)) (range 50)))))
