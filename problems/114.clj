;p114: Global take-while
;take-while is great for filtering sequences, but it is limited: you can only examine
;a single item of the sequence at a time. What if you need to keep track of some
;state as you go over the sequence? Write a function which accepts an integer n,
;a predicate p, and a sequence. It should return a lazy sequence of items in the list
;up to, but not including, the nth item that satisfies the predicate
;
(defn global-take-while
  "Returns a lazy sequence of items in col up to, but not including,
  the Nth item that satisfies the pred."
  [N pred col]
  ((fn _global-take-while
     [col result collected]
     (if (empty? col)
       result
       (let [current-item (first col)]
         (if (pred current-item)
           (if (= (dec N) collected)
             result
             (lazy-seq (cons current-item (_global-take-while (rest col) result (inc collected)))))
           (lazy-seq (cons current-item (_global-take-while (rest col) result collected)))))))
   col [] 0))

(= [2 3 5 7 11 13]
   (global-take-while 4 #(= 2 (mod % 3))
                      [2 3 5 7 11 13 17 19 23]))
(= ["this" "is" "a" "sentence"]
   (global-take-while 3 #(some #{\i} %)
                      ["this" "is" "a" "sentence" "i" "wrote"]))
(= ["this" "is"]
   (global-take-while 1 #{"a"}
                      ["this" "is" "a" "sentence" "i" "wrote"]))
