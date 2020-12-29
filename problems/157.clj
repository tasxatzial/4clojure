;p157: Indexing Sequences
;Transform a sequence into a sequence of pairs containing the original elements along with their index
;
(defn index-sequence
  [col]
  (partition 2 (interleave col (range))))

;tests
(= (index-sequence [:a :b :c]) [[:a 0] [:b 1] [:c 2]])
(= (index-sequence [0 1 3]) '((0 0) (1 1) (3 2)))
(= (index-sequence [[:foo] {:bar :baz}]) [[[:foo] 0] [{:bar :baz} 1]])
