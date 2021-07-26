package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.Base;
public class HomePage 
{
	public WebDriver driver;
	By resetButton = By.xpath("//button[contains(text(),'Reset')]");
	By weighButton = By.cssSelector("button[id='weigh']");
	By leftBowl = By.cssSelector(".board-row .square");
	By rightBowl = By.cssSelector("div:nth-child(1) div.game:nth-child(1) div.game-board:nth-child(3) .square");
	By symbolButton = By.cssSelector(".game div:nth-child(2) button");
	By coin = By.cssSelector(".coins");
	public HomePage(WebDriver driver) 
	{
		this.driver = driver;
	}	
	public WebElement getResetButton() 
	{
		return driver.findElement(resetButton);
	}
	public WebElement getWeighButton() 
	{
		return driver.findElement(weighButton);
	}
	public List<WebElement> getLeftBowl() 
	{
		return driver.findElements(leftBowl);
	}
	public List<WebElement> getRightBowl() 
	{
		return driver.findElements(rightBowl);
	}
	public WebElement getsymbolButton() 
	{
		return driver.findElement(symbolButton);
	}
	public List<WebElement> getCoins() 
	{
		return driver.findElements(coin);
	}
	
}
