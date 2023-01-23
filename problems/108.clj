;; p108: Lazy Searching

;; Given any number of sequences, each sorted from smallest to largest, find the
;; smallest single number which appears in all of the sequences. The sequences may
;; be infinite, so be careful to search lazily.

(ns p108.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn contained?
  "Returns true if xs contains n, else false.
  xs must be sorted in increasing order."
  [n xs]
  (->> xs
       (take-while #(<= % n))
       last
       (= n)))

(defn find-smallest-common
  "Returns the smallest number which appears in all seqs.
  Every sequence must be sorted in increasing order."
  ([& seqs]
   (let [rest-xss (rest seqs)]
     (loop [first-coll (first seqs)]
       (when (seq first-coll)
         (let [num (first first-coll)
               contained-in-rest (map (partial contained? num) rest-xss)]
           (if (some false? contained-in-rest)
             (recur (rest first-coll))
             num)))))))

(deftest tests
  (testing "test1"
    (is (= 3 (find-smallest-common [3 4 5]))))
  (testing "test2"
    (is (= 4 (find-smallest-common [1 2 3 4 5 6 7] [0.5 3/2 4 19]))))
  (testing "test3"
    (is (= 7 (find-smallest-common (range) (range 0 100 7/6) [2 3 5 7 11 13]))))
  (testing "test4"
    (is (= 64 (find-smallest-common (map #(* % % %) (range))
                               (filter #(zero? (bit-and % (dec %))) (range))
                               (iterate inc 20))))))
