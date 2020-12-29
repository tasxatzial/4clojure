;p54: Partition a Sequence
;Write a function which returns a sequence of lists of x items each.
;Lists of less than x items should not be returned
;
;restrictions: partition, partition-all

;solution 1: lazy
(defn my-partition1
  "Returns a sequence of lists of N items each."
  ([N col] (my-partition1 N col (Math/floor (/ (count col) N))))
  ([N col subseqs-count]
   (let [rest-subseqs (lazy-seq (my-partition1 N (drop N col) subseqs-count))]
     (take subseqs-count (concat (list (take N col)) rest-subseqs)))))

;solution 2: recursion
(defn my-partition2
  "Returns a sequence of lists of N items each."
  [N col]
  ((fn my-partition
     [result col]
     (if (< (count col) N)
       result
       (recur (concat result (list (take N col))) (drop N col))))
   '()
   col))

;tests
(= (my-partition1 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))
(= (my-partition1 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
(= (my-partition1 3 (range 8)) '((0 1 2) (3 4 5)))

(= (my-partition2 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))
(= (my-partition2 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
(= (my-partition2 3 (range 8)) '((0 1 2) (3 4 5)))
