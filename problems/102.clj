;p102: intoCamelCase
;When working with java, you often need to create an object with fieldsLikeThis, but you'd rather work with a hashmap
;that has :keys-like-this until it's time to convert. Write a function which takes lower-case hyphen-separated strings
;and converts them to camel-case strings
;
(defn capitalize-first
   "Capitalizes the first letter of s."
   [s]
   (str (clojure.string/upper-case (first s)) (apply str (rest s))))

(defn to-camel-case
  "Converts s to camel-case."
  [s]
  (let [split-s (clojure.string/split s #"-")
        capitalized-first (map capitalize-first (rest split-s))]
    (str (first split-s) (apply str capitalized-first))))

;tests
(= (to-camel-case "something") "something")
(= (to-camel-case "multi-word-key") "multiWordKey")
(= (to-camel-case "leaveMeAlone") "leaveMeAlone")
