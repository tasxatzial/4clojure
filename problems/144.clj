;p144: Oscillate
;Write an oscillating iterate: a function that takes an initial value and a variable number of
;functions. It should return a lazy sequence of the functions applied to the value in order,
;restarting from the first function after it hits the end
;
(defn oscillate
  "Returns a lazy sequence of the functions args applied to val in order,
   restarting from the first function after it hits the end."
  [val & args]
  (let [func-count (count args)]
    ((fn _oscillate
       [val i]
       (let [index (mod i func-count)]
         (cons val (lazy-seq (_oscillate ((nth args index) val) (inc index))))))
     val
     0)))

(= (take 3 (oscillate 3.14 int double)) [3.14 3 3.0])
(= (take 5 (oscillate 3 #(- % 3) #(+ 5 %))) [3 0 5 2 7])
(= (take 12 (oscillate 0 inc dec inc dec inc)) [0 1 0 1 0 1 2 1 2 1 2 3])
