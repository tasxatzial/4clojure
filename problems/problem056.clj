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
