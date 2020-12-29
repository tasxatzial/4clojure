;p96: Beauty is Symmetry
;Let us define a binary tree as "symmetric" if the left half of the tree is the mirror
;image of the right half of the tree. Write a predicate to determine whether or not a
;given binary tree is symmetric.
;
;(see p95 for a reminder on the tree representation)
;
(defn symmetric-trees?
  "Returns true if the tree that has root node the left-node is symmetric
  to the tree that has root node the right-node, false otherwise."
  [left-node right-node]
  (if (and (sequential? left-node) (sequential? right-node))
    (if (= (first left-node) (first right-node))
      (and (symmetric-trees? (second left-node) (last right-node))
           (symmetric-trees? (second right-node) (last left-node)))
      false)
    (= left-node right-node)))

(defn symmetric-tree?
  "Returns true if the tree with the specified root node is symmetric,
  false otherwise."
  [node]
  (if (nil? node)
    true
    (symmetric-trees? (second node) (last node))))

;tests
(= (symmetric-tree? '(:a (:b nil nil) (:b nil nil))) true)
(= (symmetric-tree? '(:a (:b nil nil) nil)) false)
(= (symmetric-tree? '(:a (:b nil nil) (:c nil nil))) false)
(= (symmetric-tree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                     [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
   true)
(= (symmetric-tree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                     [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
   false)
(= (symmetric-tree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                     [2 [3 nil [4 [6 nil nil] nil]] nil]])
   false)
