package br.com.helio.tasks.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import br.com.helio.tasks.enums.TipoExecucao;

public class DriverFactory {
	// private static WebDriver driver;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		protected WebDriver initialValue() {
			return initDriver();
		};
	};

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static WebDriver initDriver() {
		WebDriver driver = null;

		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.LOCAL) {
			switch (Propriedades.BROWSER) {
			case CHROME:
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case INTERNET_EXPLORER:
				driver = new InternetExplorerDriver();
				break;
			case EDGE:
				driver = new EdgeDriver();
				break;
			case OPERA:
				driver = new OperaDriver();
				break;
			case SAFARI:
				driver = new SafariDriver();
				break;
			}
		}

		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.GRID) {
			DesiredCapabilities cap = null;

			switch (Propriedades.BROWSER) {
			case CHROME:
				cap = DesiredCapabilities.chrome();
				break;
			case FIREFOX:
				cap = DesiredCapabilities.firefox();
				break;
			case INTERNET_EXPLORER:
				cap = DesiredCapabilities.internetExplorer();
				break;
			case EDGE:
				cap = DesiredCapabilities.edge();
				break;
			case OPERA:
				cap = DesiredCapabilities.operaBlink();
				break;
			case SAFARI:
				cap = DesiredCapabilities.safari();
				break;
			}

			try {
				driver = new RemoteWebDriver(new URL("http://192.168.15.5:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.err.println("Falha na conexão com o GRID.");
				e.printStackTrace();
			}
		}

		driver.manage().window().maximize();
		return driver;
	}

	public static void killDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}

		if (threadDriver != null) {
			threadDriver.remove();
		}
	}
}
