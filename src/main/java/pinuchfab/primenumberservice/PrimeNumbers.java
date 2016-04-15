package pinuchfab.primenumberservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumbers 
{
	private final int max;
	private final Method method;
	private List<Integer> result;
	private final int numberOfCores = Runtime.getRuntime().availableProcessors();
	private long timeInNanoSeconds = 0;
	private long timeInMilliSeconds = 0;
	
	enum Method
	{
		oldSchool,
		java8Parallel,
		java8ParallelWithThreadingLimit,
		java8,
	}

    public PrimeNumbers(int max, Method method) throws Exception 
    {
    	if (max <= 1)
    		throw new Exception("Must be more than 1");
    	
        this.max = max;
        this.method = method;
        
        calculatePrimes();
    }
    
    public List<Integer> calculatePrimes() throws InterruptedException, ExecutionException
    {
    	timeInNanoSeconds = System.nanoTime();
    	
    	switch(method)
    	{
    	case oldSchool:
    		result = calculateOldSchool();
    		break;
    	case java8Parallel:
    		result = calculateJava8Parallel();
    		break;
    	case java8ParallelWithThreadingLimit:
    		result = calculateJava8ParallelWithThreadingLimit();
    		break;
    	case java8:
    		result = calculateJava8();
    		break;
    	}
    	
    	timeInNanoSeconds = System.nanoTime() - timeInNanoSeconds;
    	timeInMilliSeconds = (long) Math.floor(timeInNanoSeconds * 1e-6);
    	
		return result;
    }

    private List<Integer> calculateOldSchool() 
    {
    	List<Integer> result = new ArrayList<Integer>();
    	
    	boolean isPrime = true;
    	int divide = 0;
    	
        for(int from = 2 ; from <= max; from++)
        {
        	isPrime = true;
        	divide = (int) (Math.sqrt(from));
        	
        	while(divide > 1)
        	{
        		if ( (from % divide) == 0)
        			isPrime = false;
        		
        		divide--;
        	}
        	
        	if (isPrime)
        		result.add(from);
        }
        
        return result;
    }

    private boolean isPrime(int n)
    {
    	// Basically start divisor at sqrt of n, and make sure no match is found when n % divisor == 0.
    	return n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(divisor -> n % divisor == 0);
    }
    
    private List<Integer> calculateJava8Parallel() 
    {
    	return IntStream
    		.range(1, (int) max)
    			.parallel()
    				.filter(this::isPrime)
    					.boxed()
    						.collect(Collectors.toList());
    }
    
    private List<Integer> calculateJava8ParallelWithThreadingLimit() throws InterruptedException, ExecutionException 
    {
    	ForkJoinPool pool = new ForkJoinPool(2);
    	return pool.submit(() -> calculateJava8Parallel()).get();
    }
    
    private List<Integer> calculateJava8() 
    {
    	return IntStream
    		.range(1, max)
				.filter(this::isPrime)
					.boxed()
						.collect(Collectors.toList());
    }
	
    public int getMax()
    {
    	return max;
    }
    
    public List<Integer> getResult()
    {
    	return result;
    }
    
    public String getMethod()
    {
    	return method.toString();
    }
    
    public int getNumberOfCores()
    {
    	return numberOfCores;
    }
    
    public long getTimeInNamoSeconds()
    {
    	return timeInNanoSeconds;
    }
    
    public long getTimeInMilliSeconds()
    {
    	return timeInMilliSeconds;
    }
}
