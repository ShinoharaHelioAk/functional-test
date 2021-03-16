package br.com.helio.tasks.core;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DSL {
	
	/**
	 * 
	 * @param by
	 */
	public void clicarBotao(By by) {
		DriverFactory.getDriver().findElement(by).click();
	}
	
	/**
	 * 
	 * @param by
	 * @return
	 */
	public String obterValorCampo(By by) {
		return DriverFactory.getDriver().findElement(by).getAttribute("value");
	}
	
	/**
	 * 
	 * @param tempoEmSegundos
	 * @param tipoElemento
	 * @param caminhoOuIdentificadorDoElemento
	 */
	public void aguardaCarregamentoDoElemento(int tempoEmSegundos, String tipoElemento, String caminhoOuIdentificadorDoElemento) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), tempoEmSegundos);
		switch (tipoElemento) {
		case "id":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(caminhoOuIdentificadorDoElemento)));
			break;
		
		case "xpath":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(caminhoOuIdentificadorDoElemento)));
			break;

		default:
			//Caso o parâmetro "tipoElemento" não seja nenhum dos cases acima, por padrão será reconhecido como "id". 
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(caminhoOuIdentificadorDoElemento)));
			break;
		}
	}
	
	/**
	 * 
	 * @param by
	 * @param textoADigitar
	 */
	public void escreveNoCampo(By by, String textoADigitar) {
		DriverFactory.getDriver().findElement(by).clear();
		DriverFactory.getDriver().findElement(by).sendKeys(textoADigitar);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String capturaMensagemDeSucessoOuErro(By by) {
		return DriverFactory.getDriver().findElement(by).getText();
	}
}
