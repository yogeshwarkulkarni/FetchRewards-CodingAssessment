package FetchR.FetchRewardsProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import resources.Base;

public class HomePageIGameImplementation extends Base
{
	public WebDriver driver;
	static HomePage hp;
	static HashSet<Integer> set = new HashSet<>();
	static int fakeBar= -1;
	static int Rrand,Lrand;
	
	@BeforeTest()
	public void initialized() throws IOException 
	{
		driver = initializedDriver();		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		System.out.println("\n\nURL : "+prop.getProperty("url"));
	}
	public static int generateRandomNumber()
	{
		int max = 8;
        int min = 0;
        int range = max - min + 1;
        int randNumber = (int)(Math.random() * range) + min;
        return randNumber;
	}
	public static void generateNumbers() throws InterruptedException
	{
		Rrand =generateRandomNumber();
		Lrand =generateRandomNumber();

		//Generate random gold bar until both the gold bars are different
		while(Lrand==Rrand)
		{
			Rrand = generateRandomNumber();
		}
		
		//If randomly generated left and right gold bars are not equal then add them on the set
		if(!set.contains(Lrand) && !set.contains(Rrand) )
		{
			set.add(Lrand);
			set.add(Rrand);		
		}
		
		//If anyone of them are equal then generate the random number i.e gold bar
		else
		{
			//Generate random number for left bowl until the gold bar does not exist in the set
			while(set.contains(Lrand))
			{
				Lrand =generateRandomNumber();
			}
			set.add(Lrand);
			
			//Generate random number for right bowl until the gold bar does not exist in the set
			while(set.contains(Rrand) && set.size()<8)
			{
				Rrand =generateRandomNumber();
			}
			
			set.add(Rrand);
		}
		
		//Sending gold bars to browser
		hp.getLeftBowl().get(Lrand).sendKeys(Lrand+"");		
		hp.getRightBowl().get(Rrand).sendKeys(Rrand+"");
		
		hp.getWeighButton().click();
		Thread.sleep(3000);

		String symbol = hp.getsymbolButton().getText();
		
		//If < condition satisfies which mean latest generated left bowl gold bar is a fake bar
		if(symbol.equals("<"))
		{
			fakeBar = Lrand;
			//System.out.println("LRand FakeBar : "+ fakeBar);
		}
		//If > condition satisfies which mean latest generated right bowl gold bar is a fake bar
		else if (symbol.equals(">"))
		{
			fakeBar = Rrand;
			//System.out.println("Rrand FakeBar : "+ fakeBar);
		}
		
	}
	@Test(priority=1)
	public void positiveStartGame() throws IOException, InterruptedException 
	{
		System.out.println("\n\n1.Positive Test Case");
		hp = new HomePage(driver);		
		//Execute logic until we get a fake bar
		do
		{
			generateNumbers();
			if(set.size()==8)
			{
				//System.out.println("Lrand: "+Lrand);
				break;
			}

		}while(fakeBar==-1 && set.size()<8);
		
		
		if(fakeBar!=-1)
		{
			System.out.println("Fake Bar Click :"+driver.findElement(By.cssSelector("button[id='coin_"+fakeBar+"']")).getText());
			driver.findElement(By.cssSelector("button[id='coin_"+fakeBar+"']")).click();
			
			String yay_alartText = driver.switchTo().alert().getText();
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			System.out.println("Asserting : Yay! You find it!");
			Assert.assertEquals(yay_alartText,"Yay! You find it!");
			
		}		
	}	
	
	@Test(priority=2)
	public void negativeStartGame() throws IOException, InterruptedException 
	{
		System.out.println("\n\n2.Negative Test Case- Reset and Click on Wrong Coin");
		hp = new HomePage(driver);			
		hp.getResetButton().click();
		Thread.sleep(2000);
		do
		{
			generateNumbers();

		}while(fakeBar==-1);
		int neagativeFakeBar = generateRandomNumber();
		if(fakeBar!=-1 && fakeBar!=neagativeFakeBar)
		{
			System.out.println("Actual Fake Bar : "+driver.findElement(By.cssSelector("button[id='coin_"+fakeBar+"']")).getText());
			System.out.println("Click on : "+driver.findElement(By.cssSelector("button[id='coin_"+neagativeFakeBar+"']")).getText()+" to check Negative test case");
			driver.findElement(By.cssSelector("button[id='coin_"+neagativeFakeBar+"']")).click();
			String oops_alartText = driver.switchTo().alert().getText();
			
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			System.out.println("Asserting : Oops! Try Again!");
			Assert.assertEquals(oops_alartText,"Oops! Try Again!");
			
		}
	}
	@AfterTest()
	public void tearDown() 
	{
		System.out.println("\n\nBrowser Closed- End");
		driver.close();
	}

}
