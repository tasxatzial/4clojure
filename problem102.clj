;p102: intoCamelCase
;When working with java, you often need to create an object with fieldsLikeThis, but you'd rather work with a hashmap
;that has :keys-like-this until it's time to convert. Write a function which takes lower-case hyphen-separated strings
;and converts them to camel-case strings
(def p102 (fn [s]
            (let [res (memoize (fn [s]
                                 (clojure.string/split s #"-")))]
              (str (first (res s))
                   (apply str (map #(str (clojure.string/upper-case (first %)) (apply str (rest %)))
                                   (rest (res s))))))))

;tests
(p102 "something")
(p102 "multi-word-key")
(p102 "leaveMeAlone")