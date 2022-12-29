;; p56: Find Distinct Items

;; Write a function which removes the duplicates from a sequence. Order of the items must be maintained
;; restrictions: distinct

(defn remove-duplicates
  ([xs]
   (remove-duplicates xs #{} []))
  ([xs set-xs result]
   (if (empty? xs)
     result
     (if (contains? set-xs (first xs))
       (recur (rest xs) set-xs result)
       (let [new-set-xs (conj set-xs (first xs))
             new-result (conj result (first xs))]
         (recur (rest xs) new-set-xs new-result))))))

(deftest tests
  (testing "test1"
    (is (= (remove-duplicates [1 2 1 3 1 2 4]) [1 2 3 4])))
  (testing "test2"
    (is (= (remove-duplicates [:a :a :b :b :c :c]) [:a :b :c])))
  (testing "test3"
    (is (= (remove-duplicates '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))))
  (testing "test4"
    (is (= (remove-duplicates (range 50)) (range 50)))))
