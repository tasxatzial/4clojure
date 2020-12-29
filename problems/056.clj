;p56: Find Distinct Items
;Write a function which removes the duplicates from a sequence. Order of the items must be maintained
;
;restrictions: distinct
;
(defn my-distinct
  "Removes the duplicates from a sequence."
  ([col] (my-distinct col #{} []))
  ([col set-result result]
   (if (empty? col)
     result
     (if (set-result (first col))
       (recur (rest col) set-result result)
       (let [new-set-result (conj set-result (first col))
             new-result (conj result (first col))]
         (recur (rest col) new-set-result new-result))))))

;tests
(= (my-distinct [1 2 1 3 1 2 4]) [1 2 3 4])
(= (my-distinct [:a :a :b :b :c :c]) [:a :b :c])
(= (my-distinct '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
(= (my-distinct (range 50)) (range 50))
