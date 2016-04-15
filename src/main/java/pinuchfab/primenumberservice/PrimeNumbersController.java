package pinuchfab.primenumberservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pinuchfab.primenumberservice.PrimeNumbers.Method;

@RestController
@RequestMapping("/primeNumbers")
public class PrimeNumbersController 
{
    @RequestMapping(method=RequestMethod.GET)
    public List<PrimeNumbers> primeNumbers(@RequestParam(value="max", defaultValue="100") Integer max, @RequestParam(value="method", required = false) Method method) throws Exception 
    {
    	List<PrimeNumbers> primes = new ArrayList<PrimeNumbers>();
    	
    	// If a method is requested, then use it.
    	if (method != null)
    		primes.add(new PrimeNumbers(max, method)); 
    	// Otherwise run for all methods, this is good for comparing which one is the fastest.
    	else
    	{
    		primes.add(new PrimeNumbers(max, Method.oldSchool)); 
    		primes.add(new PrimeNumbers(max, Method.java8)); 
    		primes.add(new PrimeNumbers(max, Method.java8Parallel)); 
    		primes.add(new PrimeNumbers(max, Method.java8ParallelWithThreadingLimit)); 
    	}
    	
        return primes;
    }
}
