;; p41: Drop Every Nth Item

;; Write a function which drops every Nth item from a sequence

(defn drop-every-nth
  [xs N]
  (letfn [(not-nth-element? [idx]
            (pos? (mod (inc idx) N)))]
    (keep-indexed #(if (not-nth-element? %1) %2)
                  xs)))

(defn drop-every-nth2
  ([xs N]
   (drop-every-nth2 xs N 0 []))
  ([xs N idx result]
   (if (empty? xs)
     result
     (if (= 0 (mod (inc idx) N))
       (recur (rest xs) N (inc idx) result)
       (recur (rest xs) N (inc idx) (conj result (first xs)))))))

;; testing drop-every-nth -----------------------------------
(clojure.test/deftest test1-drop-every-nth
  (clojure.test/testing
    (clojure.test/is (= (drop-every-nth [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8]))))

(clojure.test/deftest test2-drop-every-nth
  (clojure.test/testing
    (clojure.test/is (= (drop-every-nth [:a :b :c :d :e :f] 2) [:a :c :e]))))

(clojure.test/deftest test3-drop-every-nth
  (clojure.test/testing
    (clojure.test/is (= (drop-every-nth [1 2 3 4 5 6] 4) [1 2 3 5 6]))))

;; testing drop-every-nth2 -----------------------------------
(clojure.test/deftest test1-drop-every-nth2
  (clojure.test/testing
    (clojure.test/is (= (drop-every-nth2 [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8]))))

(clojure.test/deftest test2-drop-every-nth2
  (clojure.test/testing
    (clojure.test/is (= (drop-every-nth2 [:a :b :c :d :e :f] 2) [:a :c :e]))))

(clojure.test/deftest test3-drop-every-nth2
  (clojure.test/testing
    (clojure.test/is (= (drop-every-nth2 [1 2 3 4 5 6] 4) [1 2 3 5 6]))))
