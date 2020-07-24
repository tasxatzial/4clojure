;p50: Split by Type
;Write a function which takes a sequence consisting of items with different types and splits them up into a set of
;homogeneous sub-sequences. The internal order of each sub-sequence should be maintained, but the sub-sequences
;themselves can be returned in any order (this is why 'set' is used in the test cases)

;solution 1 ----------------------------------------
(def p50 (fn [col]
           (map #(apply vector %)
                (filter #(not-empty %)
                        (map #(filter % col) [keyword? number? string? vector?])))))

;solution 1 tests
(set (p50 [1 2 3 :a]))
(set (p50 [:a "foo"  "bar" :b]))
(set (p50 [[1 2] :a [3 4] 5 6 :b]))

;solution 2 ----------------------------------------
(def p50_2 (fn [col]
             (filter #(not (nil? %))
                     (map #(% true)
                          (map #(group-by % col) [keyword? number? string? vector?])))))

;solution 2 tests
(set (p50_2 [1 2 3 :a]))
(set (p50_2 [:a "foo"  "bar" :b]))
(set (p50_2 [[1 2] :a [3 4] 5 6 :b]))