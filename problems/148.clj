;p148: The Big Divide
;Write a function which calculates the sum of all natural numbers under n (first argument) which
;are evenly divisible by at least one of a and b (second and third argument). Numbers a and b are
;guaranteed to be coprimes. Note: Some test cases have a very large n, so the most obvious solution
;will exceed the time limit
;

;To solve this problem efficiently, we note that all numbers < N which are divisible by
;X form a arithmetic progression. The formula for the sum is easily found to be:
;
;X * q * (q + 1) / 2
;
;where q = the total numbers < N that divide X:
;q = (quot (N-1) X)
;

(defn sum-of-divisible
  "Calculates the sum of numbers under N that are divisible by X."
  [X N]
   (let [divisible-count (quot (dec N) X)]
     (*' X (/ (*' divisible-count (inc divisible-count)) 2))))

(defn big-divide
  "Calculates the sum of all natural numbers under N which are evenly
   divisible by at least one of a and b. Numbers a and b are coprimes."
  [N a b]
  (let [div-by-a (sum-of-divisible a N)
        div-by-b (sum-of-divisible b N)
        div-by-ab (sum-of-divisible (* a b) N)]
    (- (+ div-by-a div-by-b) div-by-ab)))

(= 0 (big-divide 3 17 11))
(= 23 (big-divide 10 3 5))
(= 233168 (big-divide 1000 3 5))
(= "2333333316666668" (str (big-divide 100000000 3 5)))
(= "110389610389889610389610"
   (str (big-divide (* 10000 10000 10000) 7 11)))
(= "1277732511922987429116"
   (str (big-divide (* 10000 10000 10000) 757 809)))
(= "4530161696788274281"
   (str (big-divide (* 10000 10000 1000) 1597 3571)))
