;p47: Contain Yourself
;The contains? function checks if a KEY is present in a given collection.
;This often leads beginner clojurians to use it incorrectly with numerically indexed collections like vectors and lists
(contains? #{4 5 6} 4)                                      ;true; 4
(contains? [1 1 1 1 1] 4)                                   ;true, 4th index
(contains? {4 :a 2 :b} 4)                                   ;true, 4
(not (contains? [1 2 4] 4))                                 ;true, 4th index