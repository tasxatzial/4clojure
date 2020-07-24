;p145: For the win
;Clojure's for macro is a tremendously versatile mechanism for producing a sequence based on some other sequence(s).
;It can take some time to understand how to use it properly, but that investment will be paid back with clear, concise
;sequence-wrangling later. With that in mind, read over these for expressions and try to see how each of them produces
;the same result
(= (range 1 40 4) (for [x (range 40)                        ;(range 1 40 4)
            :when (= 1 (rem x 4))]
        x))
(= (range 1 40 4) (for [x (iterate #(+ 4 %) 0)              ;(range 1 40 4)
            :let [z (inc x)]
            :while (< z 40)]
        z))
(= (range 1 40 4) (for [[x y] (partition 2 (range 20))]     ;(range 1 40 4)
        (+ x y)))