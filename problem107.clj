;p107: Simple closures
;Lexical scope and first-class functions are two of the most basic building blocks of a functional language like
;Clojure. When you combine the two together, you get something very powerful called lexical closures. With these,
;you can exercise a great deal of control over the lifetime of your local bindings, saving their values for use
;later, long after the code you're running now has finished.
;It can be hard to follow in the abstract, so let's build a simple closure. Given a positive integer n, return a
;function (f x) which computes x^n. Observe that the effect of this is to preserve the value of n for use outside
;the scope in which it is defined

;solution 1 ----------------------------------------
(def p107 (fn [n]
            #((fn my-pow [res n]
                (if (= 0 n)
                  res
                  (recur (* res %) (- n 1))))
              1 n)))

;solution 1 tests
((p107 2) 16)
(map (p107 3) [1 2 3 4])
(map #((p107 %) 2) [0 1 2 3 4])

;solution 2 ----------------------------------------
(def p107_2 (fn [n]
              #(Math/round (Math/pow % n))))

;solution 2 tests
((p107_2 2) 16)
(map (p107_2 3) [1 2 3 4])
(map #((p107_2 %) 2) [0 1 2 3 4])