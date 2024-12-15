## Simulated annealing

Is a heuristic algorithm. Mimics the actual annealing process in metallurgy. The process involves heating a material and then slowly cooling it to decrease defects,
thus reaching closer to the desired state of the material.

Similarly, the algorithm starts with a high temperature(high energy state)[an initial solution] and then slowly decreases[using a control parameter] it to find the global minimum[optimal solution].

### Example usages

- Travelling salesman problem
- Protein folding
- Job shop scheduling

## Algorithm

0. Define the problem to maximize or minimize. `f(x, y) = x^2 + y^2`
1. Start with an initial solution. `x=0, y=0 => f(x, y) = 0`
2. Start with a high temperature and a `k` constant. `temp = 100, k=1`
3. Define the perturbation function. `x' = x + a, y' = y + b; a, b = temp * [-1, 0, 1]`
4. Define the acceptance probability function and delta. `P = p(x,y,x',y')`
   Our function itself is fine, but we can use "exponential decay" as defined by Boltzmann-probability distribution.
   `P  = exp(-f(x,y)/k*temp)` and hence `P_delta = exp(-(f(x, y) - f(x', y'))/k*temp)`. 
   Here to keep our checks simple, we can use `F = f(x', y') - f(x, y)`
   1. If `F < threshold`, accept the new solution. Modify the temperature and continue... `temp = temp * coolingRate = temp / factor, coolingRate is generally (0,1)`
   2. If `F >= threshold`
      1. Don't accept the new solution and try with a new set of perturbations. (MORE COMMON)
      2. Or accept the new solution with a probability `P_delta > random(0, 1)`
5. **Update** : If the new solution is accepted, update the current solution. `x = x', y = y'` and `temp = temp * coolingRate`
6. **Temp scheduling** : Repeat step 3 through 5 until the temperature reaches a threshold. `temp > threshold`
   1. Other decay functions:
      1. Exponential decay: `temp = temp * coolingRate, coolingRate is generally (0,1)`
      2. Linear decay: `temp = temp - coolingRate`
      3. Logarithmic decay: `temp = temp / log(coolingRate)`
```
// Source: BaelDung
algorithm AcceptanceFunction(T, ΔE):
    // INPUT
    //    T = the temperature
    //    ΔE = the energy variation between the new candidate solution and the current one
    // OUTPUT
    //    Returns true if the new solution is accepted. Otherwise, returns false.

    if ΔE < 0:
        return true
    else:
        r <- generate a random value in the range [0, 1)
        if r < exp(-ΔE / T):
            return true
        else:
            return false

algorithm SimulatedAnnealingOptimizer(T_max, T_min, E_th, α):
    // INPUT
    //    T_max = the maximum temperature
    //    T_min = the minimum temperature for stopping the algorithm
    //    E_th = the energy threshold to stop the algorithm
    //    alpha = the cooling factor
    // OUTPUT
    //    The best found solution

    T <- T_max
    x <- generate the initial candidate solution
    E <- E(x)  // compute the energy of the initial solution

    while T > T_min and E > E_th:
        x_new <- generate a new candidate solution
        E_new <- compute the energy of the new candidate x_new
        ΔE <- E_new - E

        if Accept(ΔE, T):
            x <- x_new
            E <- E_new

        T <- T / alpha  // cool the temperature

    return x
```

### Example

```java
// leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/
// leetcode.com/problems/best-position-for-a-service-centre/
public class Solution {
    public double getMinDistSum(int [][]positions){
        double res = Double.MAX_VALUE;

        double x=50, y=50, delta = 50;
        double resx = 50, resy = 50;
        while(delta >= 1e-6) {
            boolean found = false;
            for(int i=-1; i<=1; i++) {
                for(int j=-1; j<=1; j++) {
                    double nx = resx + delta*i;
                    double ny = resy + delta*j;
                    double tmp = 0;
                    for(int []pos : positions) {
                        tmp += Math.sqrt((pos[0]-nx)*(pos[0]-nx) + (pos[1]-ny)*(pos[1]-ny));
                    }
                    if(tmp < res) {
                        res = tmp;
                        x = nx;
                        y = ny;
                        found = true;
                    }
                }
            }
            if(!found) {
                delta /= 2;
            } else {
                resx = x;
                resy = y;
            }
        }
        return res;
    }
}
```


References:

- [CODE](./SimAnnealing.java)
- https://www.baeldung.com/cs/simulated-annealing