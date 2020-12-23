;p50: Split by Type
;Write a function which takes a sequence consisting of items with different types and splits them up into a set of
;homogeneous sub-sequences. The internal order of each sub-sequence should be maintained, but the sub-sequences
;themselves can be returned in any order (this is why 'set' is used in the test cases)

(defn type-split
  "Splits col by type."
   [col]
  (->>
   [keyword? number? string? vector?]
   (map #(filter % col))
   (filter not-empty)
   (map #(apply vector %))))

(= (set (type-split [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})
(= (set (type-split [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})
(= (set (type-split [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})
