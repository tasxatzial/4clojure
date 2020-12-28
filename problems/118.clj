;p118: Re-implement Map
;Map is one of the core elements of a functional programming language.
;Given a function f and an input sequence s, return a lazy sequence
;of (f x) for each element x in s
;
;restrictions: map, map-indexed, mapcat, for
;
(defn my-map
  "Returns a lazy sequence of (f x) for each element x in col."
  [f col]
  (if (empty? col)
    nil
    (lazy-seq (cons (f (first col)) (my-map f (next col))))))

(= [3 4 5 6 7]
   (my-map inc [2 3 4 5 6]))
(= (repeat 10 nil)
   (my-map (fn [_] nil) (range 10)))
(= [1000000 1000001]
   (->> (my-map inc (range))
        (drop (dec 1000000))
        (take 2)))
