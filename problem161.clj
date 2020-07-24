;p161: Subset and Superset
;Set A is a subset of set B, or equivalently B is a superset of A, if A is "contained" inside B. A and B may coincide
(clojure.set/superset? #{1 2} #{2})
(clojure.set/subset? #{1} #{1 2})
(clojure.set/superset? #{1 2} #{1 2})
(clojure.set/subset? #{1 2} #{1 2})