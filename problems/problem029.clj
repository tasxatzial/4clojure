;p29: Get the Caps
;Write a function which takes a string and returns a new string containing only the capital letters

;solution 1 ----------------------------------------
(def p29 (fn [col]
           (apply str
                  (filter #(and (re-find #"[A-Z]" %) (= (clojure.string/upper-case %) %))
                          (clojure.string/split col, #"")))))

;solution 1 tests
(map p29 ["HeLlO, WoRlD!" "nothing" "$#A(*&987Zf"])

;solution 2 ----------------------------------------
(def p29_2 (fn [col]
             (apply str
                    (let [matcher (re-matcher #"[A-Z]" col)]
                      ((fn find-all [has-next result]
                         (if has-next
                           (let [match (re-find matcher)]
                             (recur match (concat result [has-next])))
                           result))
                       (re-find matcher) '())))))

;solution 2 tests
(map p29_2 ["HeLlO, WoRlD!" "nothing" "$#A(*&987Zf"])

;solution 3 ----------------------------------------
(def p29_3 (fn [col]
             (apply str
                    (re-seq #"[A-Z]" col))))

;solution 3 tests
(map p29_3 ["HeLlO, WoRlD!" "nothing" "$#A(*&987Zf"])