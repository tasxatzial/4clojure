;p96: Beauty is Symmetry
;Let us define a binary tree as "symmetric" if the left half of the tree is the mirror image of the right half of the
;tree. Write a predicate to determine whether or not a given binary tree is symmetric. (see p95: To Tree, or not to Tree
;for a reminder on the tree representation we're using)
(def p96 (fn sym-check [col]
           (if (nil? (first col))
             true
             ((fn sym-check [left-tree right-tree]
                (if (and (sequential? left-tree) (sequential? right-tree))
                  (if (and (sym-check (second left-tree) (nth right-tree 2))
                           (sym-check (second right-tree) (nth left-tree 2)))
                    (= (first left-tree) (first right-tree))
                    false)
                  (= left-tree right-tree)))
              (second col) (nth col 2)))))

;tests
(p96 '(:a (:b nil nil) (:b nil nil)))                       ;true
(p96 '(:a (:b nil nil) nil))                                ;false
(p96 '(:a (:b nil nil) (:c nil nil)))                       ;false
(p96 [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]         ;true
      [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
(p96 [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]         ;false
      [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
(p96 [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]         ;false
      [2 [3 nil [4 [6 nil nil] nil]] nil]])