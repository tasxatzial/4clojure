;p105: Identify keys and values
;Given an input sequence of keywords and numbers, create a map such that each
;key in the map is a keyword, and the value is a sequence of all the numbers (if any)
;between it and the next keyword in the sequence
;
(defn identify-keys-vals
  ([col] (identify-keys-vals col {}))
  ([col result]
   (if (empty? col)
     result
     (let [key (first col)
           nums (take-while #(not (keyword? %)) (rest col))
           new-result (assoc result key nums)
           new-col (drop (inc (count nums)) col)]
       (recur new-col new-result)))))

(= {} (identify-keys-vals []))
(= {:a [1]} (identify-keys-vals [:a 1]))
(= {:a [1], :b [2]} (identify-keys-vals [:a 1, :b 2]))
(= {:a [1 2 3], :b [], :c [4]} (identify-keys-vals [:a 1 2 3 :b :c 4]))
