;p112: Sequs Horribilis
;Create a function which takes an integer and a nested collection of integers as
;arguments. Analyze the elements of the input collection and return a sequence which
;maintains the nested structure, and which includes all elements starting from the
;head whose sum is less than or equal to the input integer
;
(defn sequs-horribilis
  [N col]
  ((fn _sequs-horribilis
     [col sum result]
     (if (empty? col)
       result
       (let [current-element (first col)]
         (if (sequential? current-element)
           (let [partial-seq (_sequs-horribilis current-element sum '())
                 new-sum (+ sum (reduce + (flatten partial-seq)))
                 new-result (concat result [partial-seq])]
             (recur (next col) new-sum new-result))
           (if (< N (+ sum current-element))
             result
             (let [new-sum (+ sum current-element)
                   new-result (concat result [current-element])]
               (recur (next col) new-sum new-result)))))))
   col 0 '()))

;tests
(=  (sequs-horribilis 10 [1 2 [3 [4 5] 6] 7])
    '(1 2 (3 (4))))
(=  (sequs-horribilis 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])
    '(1 2 (3 (4 (5 (6 (7)))))))
(=  (sequs-horribilis 9 (range))
    '(0 1 2 3))
(=  (sequs-horribilis 1 [[[[[1]]]]])
    '(((((1))))))
(=  (sequs-horribilis 0 [1 2 [3 [4 5] 6] 7])
    '())
(=  (sequs-horribilis 0 [0 0 [0 [0]]])
    '(0 0 (0 (0))))
(=  (sequs-horribilis 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])
    '(-10 (1 (2 3 (4)))))
