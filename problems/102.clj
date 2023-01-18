;; p102: intoCamelCase

;; When working with java, you often need to create an object with fieldsLikeThis,
;; but you'd rather work with a hashmap that has :keys-like-this until it's time to
;; convert. Write a function which takes lower-case hyphen-separated strings and
;; converts them to camel-case strings.

(ns p102.core
  (:require [clojure.test :refer [deftest testing is]]))

(defn into-camel-case
  [s]
  (clojure.string/replace s #"(-)([a-z])" #(clojure.string/upper-case (get %1 2))))

(deftest tests
  (testing "test1"
    (is (= (into-camel-case "something") "something")))
  (testing "test2"
    (is (= (into-camel-case "multi-word-key") "multiWordKey")))
  (testing "test3"
    (is (= (into-camel-case "leaveMeAlone") "leaveMeAlone"))))
