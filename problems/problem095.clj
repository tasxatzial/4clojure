;p95: To Tree, or not to Tree
;Write a predicate which checks whether or not a given sequence represents a binary tree. Each node in the tree must
;have a value, a left child, and a right child
(def p95 (fn tree-check [node]
                     (if (nil? node)
                       true
                       (if (sequential? node)
                         (if (and (= (count node) 3)
                                  (tree-check (second node))
                                  (tree-check (nth node 2)))
                           true
                           false)
                         (if node
                           true
                           false)))))

;tests
(p95 '(:a (:b nil nil) nil))
(p95 '(:a (:b nil nil)))
(p95 [1 nil [2 [3 nil nil] [4 nil nil]]])
(p95 [1 [2 nil nil] [3 nil nil] [4 nil nil]])
(p95 [1 [2 [3 [4 nil nil] nil] nil] nil])
(p95 [1 [2 [3 [4 false nil] nil] nil] nil])
(p95 '(:a nil ()))