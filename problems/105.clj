;; p105: Identify keys and values

;; Given an input sequence of keywords and numbers, create a map such that each
;; key in the map is a keyword, and the value is a sequence of all the numbers
;; (if any) between it and the next keyword in the sequence.

(defn identify-keys-vals
  [xs]
  (loop [result {}
         xs xs]
    (if (seq xs)
      (let [key (first xs)
            values (take-while (complement keyword?) (rest xs))]
        (recur (assoc result key values)
               (drop (inc (count values)) xs)))
      result)))

(deftest tests
  (testing "test1"
    (is (= {} (identify-keys-vals []))))
  (testing "test2"
    (is (= {:a [1]} (identify-keys-vals [:a 1]))))
  (testing "test3"
    (is (= {:a [1], :b [2]} (identify-keys-vals [:a 1, :b 2]))))
  (testing "test4"
    (is (= {:a [1 2 3], :b [], :c [4]} (identify-keys-vals [:a 1 2 3 :b :c 4])))))
