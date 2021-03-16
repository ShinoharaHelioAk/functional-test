package br.com.helio.tasks.functional.page;

import org.openqa.selenium.By;

import br.com.helio.tasks.core.BasePage;

public class TasksPage extends BasePage {
	/**
	 * 
	 */
	public void clicarNoBotaoAddTodo() {
		dsl.clicarBotao(By.id("addTodo"));
	}
	
	/**
	 * 
	 */
	public void clicarNoBotaoSave() {
		dsl.clicarBotao(By.id("saveButton"));
	}
	
	/**
	 * 
	 * @param tempoEmSegundos
	 */
	public void aguardaCarregamentoDoBotaoSavePorId(int tempoEmSegundos) {
		dsl.aguardaCarregamentoDoElemento(tempoEmSegundos, "id", "saveButton");
	}
	
	/**
	 * 
	 * @param tempoEmSegundos
	 */
	public void aguardaCarregamentoDaMensagemDeSucessoSuccess(int tempoEmSegundos) {
		dsl.aguardaCarregamentoDoElemento(tempoEmSegundos, "id", "message");
	}
	
	/**
	 * 
	 * @param tempoEmSegundos
	 */
	public void aguardaCarregamentoDaMensagemDeErroDueDateMustNotBeInPast(int tempoEmSegundos) {
		dsl.aguardaCarregamentoDoElemento(tempoEmSegundos, "id", "message");
	}
	
	/**
	 * 
	 * @param tempoEmSegundos
	 */
	public void aguardaCarregamentoDaMensagemDeErroFillTheTaskDescription(int tempoEmSegundos) {
		dsl.aguardaCarregamentoDoElemento(tempoEmSegundos, "id", "message");
	}
	
	/**
	 * 
	 * @param tempoEmSegundos
	 */
	public void aguardaCarregamentoDaMensagemDeErroFillTheDueDate(int tempoEmSegundos) {
		dsl.aguardaCarregamentoDoElemento(tempoEmSegundos, "id", "message");
	}
	
	/**
	 * 
	 * @param textoADigitar
	 */
	public void escreveNoCampoTask(String textoADigitar) {
		dsl.escreveNoCampo(By.id("task"), textoADigitar);
	}
	
	/**
	 * 
	 * @param textoADigitar
	 */
	public void escreveNoCampoDueDate(String textoADigitar) {
		dsl.escreveNoCampo(By.id("dueDate"), textoADigitar);
	}

	/**
	 * 
	 * @return
	 */
	public String obterMensagemDeSucesso() {
		return dsl.capturaMensagemDeSucessoOuErro(By.id("message"));
	}
	
	/**
	 * 
	 * @return
	 */
	public String obterMensagemDeErro() {
		return dsl.capturaMensagemDeSucessoOuErro(By.id("message"));
	}
}
