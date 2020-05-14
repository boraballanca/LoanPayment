/**This class contains the formulas needed to calculate the annual payment
  and the monthly payment to a loan*/
public class Formulas {
	private double annualPayment;
	private double monthlyPayment;
	public double computeMonthlyPayment(double principle, double interest, int years)
	{
	   annualPayment=(Math.pow((1+interest), years)*principle*interest)/(Math.pow((1+interest),years)-1);
	   monthlyPayment=annualPayment/12;
	   return monthlyPayment;

	}

}
