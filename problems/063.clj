;p63: Group a Sequence
;Given a function f and a sequence s, write a function which returns a map.
;The keys should be the values of f applied to each item in s.
;The value at each key should be a vector of corresponding items in the order they appear in s
;
;restrictions: group-by
;
(defn map-f
  "Maps func to each item in col and returns [x, func(x)] for each item in col."
  [func col]
  (into [] (map #(vector % (func %)) col)))

(defn my-group-by
  "Returns a map of the elements of col keyed by the result of
  func on each element. The value at each key will be a vector of the
  corresponding elements, in the order they appeared in col."
  [func col]
  (reduce (fn [result x]
            (let [func-el (get x 1)
                  el (get x 0)]
              (if (contains? result func-el)
                (assoc result func-el (conj (result func-el) el))
                (assoc result func-el [el]))))
          {}
          (map-f func col)))

;tests
(= (my-group-by #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
(= (my-group-by #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})
(= (my-group-by count [[1] [1 2] [3] [1 2 3] [2 3]])
   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})
