;p134: A nil key
;Write a function which, given a key and map, returns true iff the map contains an entry with
;that key and its value is nil
;
(defn my-nil?
  [key col]
  (and (contains? col key) (nil? (col key))))

;tests
(true?  (my-nil? :a {:a nil :b 2}))
(false? (my-nil? :b {:a nil :b 2}))
(false? (my-nil? :c {:a nil :b 2}))
