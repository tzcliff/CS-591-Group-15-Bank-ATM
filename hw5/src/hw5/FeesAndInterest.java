package hw5;

public class FeesAndInterest {

	public static long Withdrawalfee(AccountType type)
	{
		if (type == AccountType.Checking)
		{
			return 10;
		}else
		{
			return 0;
		}
	}
	
	public static long AccountInterest (AccountType type, long amount)
	{
		if (type == AccountType.Saving && amount > 0)
		{
			return (long)(amount * 0.025);
		}
		else
			return 0;
	}
}
