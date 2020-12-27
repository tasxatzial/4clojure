;p95: To Tree, or not to Tree
;Write a predicate which checks whether or not a given sequence represents a binary tree.
;Each node in the tree must have a value, a left child, and a right child
;
(defn binary-tree?
  "Returns true if node is the root node of a binary tree."
  [node]
  (if (nil? node)
    true
    (if (sequential? node)
      (if (and (= (count node) 3)
               (binary-tree? (second node))
               (binary-tree? (last node)))
        true
        false)
      false)))

(= (binary-tree? '(:a (:b nil nil) nil))
   true)
(= (binary-tree? '(:a (:b nil nil)))
   false)
(= (binary-tree? [1 nil [2 [3 nil nil] [4 nil nil]]])
   true)
(= (binary-tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]])
   false)
(= (binary-tree? [1 [2 [3 [4 nil nil] nil] nil] nil])
   true)
(= (binary-tree? [1 [2 [3 [4 false nil] nil] nil] nil])
   false)
(= (binary-tree? '(:a nil ()))
   false)
