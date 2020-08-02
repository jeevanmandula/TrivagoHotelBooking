package trivago.hotelBooking.utils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class RetryFailedTestCases implements IRetryAnalyzer
{
	private int retryCnt = 0; 	
	private int maxRetryCnt = 1;
	public boolean retry(ITestResult result)
	{
		if (!result.isSuccess())
		{
			if (retryCnt < maxRetryCnt) 
			{
				System.out.println("Retrying "+ result.getName()+ "angain and the count is"  + (retryCnt+1));
				retryCnt++;
				return true;
		}
	}
	return false; 
	}
}
