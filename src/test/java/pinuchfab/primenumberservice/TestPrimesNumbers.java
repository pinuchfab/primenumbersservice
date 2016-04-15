package pinuchfab.primenumberservice;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import pinuchfab.primenumberservice.PrimeNumbers.Method;

public class TestPrimesNumbers 
{
	@Test
	public void testForTwenty() throws Exception 
	{
		// Expect {2, 3, 5, 7, 11, 13, 17, 19}.
		int[] expected = {2, 3, 5, 7, 11, 13, 17, 19};
				
		//
		// Old school.
		//
		
		PrimeNumbers primeNumbers = new PrimeNumbers(20, Method.oldSchool);
		List<Integer> result = primeNumbers.getResult();
		
		int[] resultAsArr = result.stream().mapToInt(i -> i).toArray();
		assertArrayEquals(expected, resultAsArr);
		
		//
		// Java 8 parallel.
		//
		
		primeNumbers = new PrimeNumbers(20, Method.java8Parallel);
		result = primeNumbers.getResult();
		
		// Expect 19, 17, 13, 11, 7, 5, 3, 2.
		resultAsArr = result.stream().mapToInt(i -> i).toArray();
		assertArrayEquals(expected, resultAsArr);
		
		//
		// Java 8 parallel with threading limit.
		//
		
		primeNumbers = new PrimeNumbers(20, Method.java8ParallelWithThreadingLimit);
		result = primeNumbers.getResult();
		
		resultAsArr = result.stream().mapToInt(i -> i).toArray();
		assertArrayEquals(expected, resultAsArr);
	}
	
	@Test
	public void testForZero() throws Exception 
	{		
		//
		// Expect exception with string "Must be more than 1".
		//
		
		//
		// Old school.
		//
		
		try
		{
			new PrimeNumbers(0, Method.oldSchool);
		}
		catch(Exception e)
		{
			assertEquals("Must be more than 1", e.getMessage());
		}
		
		//
		// Java 8 parallel.
		//
		
		try
		{
			new PrimeNumbers(0, Method.java8Parallel);
		}
		catch(Exception e)
		{
			assertEquals("Must be more than 1", e.getMessage());
		}
		
		//
		// Java 8 parallel with threading limit.
		//
		
		try
		{
			new PrimeNumbers(0, Method.java8ParallelWithThreadingLimit);
		}
		catch(Exception e)
		{
			assertEquals("Must be more than 1", e.getMessage());
		}
	}
	
	@Test
	public void testForOne() throws Exception 
	{		
		//
		// Expect exception with string "Must be more than 1".
		//
		
		//
		// Old school.
		//
		
		try
		{
			new PrimeNumbers(1, Method.oldSchool);
		}
		catch(Exception e)
		{
			assertEquals("Must be more than 1", e.getMessage());
		}
		
		//
		// Java 8 parallel.
		//
		
		try
		{
			new PrimeNumbers(1, Method.java8Parallel);
		}
		catch(Exception e)
		{
			assertEquals("Must be more than 1", e.getMessage());
		}
		
		//
		// Java 8 parallel with threading limit.
		//
		
		try
		{
			new PrimeNumbers(1, Method.java8ParallelWithThreadingLimit);
		}
		catch(Exception e)
		{
			assertEquals("Must be more than 1", e.getMessage());
		}
	}
	
	public void testForNegative() throws Exception 
	{		
		//
		// Expect exception with string "Must be more than 1".
		//
		
		//
		// Old school.
		//
		
		try
		{
			new PrimeNumbers(-1, Method.oldSchool);
		}
		catch(Exception e)
		{
			assertEquals("Must be more than 1", e.getMessage());
		}
		
		//
		// Java 8 parallel.
		//
		
		try
		{
			new PrimeNumbers(-1, Method.java8Parallel);
		}
		catch(Exception e)
		{
			assertEquals("Must be more than 1", e.getMessage());
		}
		
		//
		// Java 8 parallel with threading limit.
		//
		
		try
		{
			new PrimeNumbers(-1, Method.java8ParallelWithThreadingLimit);
		}
		catch(Exception e)
		{
			assertEquals("Must be more than 1", e.getMessage());
		}
	}
}
