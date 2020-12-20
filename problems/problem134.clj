;p134: A nil key
;Write a function which, given a key and map, returns true iff the map contains an entry with that key and its
;value is nil
(def p134 (fn [key col]
            (and (contains? col key) (nil? (col key)))))

;tests
(p134 :a {:a nil :b 2})
(p134 :b {:a nil :b 2})
(p134 :c {:a nil :b 2})