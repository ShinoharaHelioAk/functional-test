package br.com.helio.tasks.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
	@Rule
	public TestName testName = new TestName();
	
	@After
	public void finaliza() throws IOException {
		TakesScreenshot print = (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = print.getScreenshotAs(OutputType.FILE);
		
		String novoArquivo = "target" + File.separator + "screenshots" + File.separator + testName.getMethodName() + ".jpg";
		FileUtils.copyFile(arquivo, new File(novoArquivo));
		
		if (Propriedades.FECHAR_BROWSER) {
			DriverFactory.killDriver();
		}
	}
}
