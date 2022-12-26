;; p29: Get the Caps

;; Write a function which takes a string and returns a new string containing only the capital letters

(defn get-capital
  [s]
  (letfn [(capital? [x]
            (<= (int \A) (int x) (int \Z)))]
    (apply str (filter capital? s))))

(defn get-capital2
  [s]
  (apply str (re-seq #"[A-Z]" s)))

;; testing get-capital -----------------------------------
(clojure.test/deftest test1-get-capital
  (clojure.test/testing
    (clojure.test/is (= (get-capital "HeLlO, WoRlD!") "HLOWRD"))))

(clojure.test/deftest test2-get-capital
  (clojure.test/testing
    (clojure.test/is (empty? (get-capital "nothing")))))

(clojure.test/deftest test3-get-capital
  (clojure.test/testing
    (clojure.test/is (= (get-capital "$#A(*&987Zf") "AZ"))))

;; testing get-capital2 -----------------------------------
(clojure.test/deftest test1-get-capital2
  (clojure.test/testing
    (clojure.test/is (= (get-capital2 "HeLlO, WoRlD!") "HLOWRD"))))

(clojure.test/deftest test2-get-capital2
  (clojure.test/testing
    (clojure.test/is (empty? (get-capital2 "nothing")))))

(clojure.test/deftest test3-get-capital2
  (clojure.test/testing
    (clojure.test/is (= (get-capital2 "$#A(*&987Zf") "AZ"))))
