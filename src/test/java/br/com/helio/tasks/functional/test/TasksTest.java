package br.com.helio.tasks.functional.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.helio.tasks.core.BaseTest;
import br.com.helio.tasks.core.DSL;
import br.com.helio.tasks.core.DriverFactory;
import br.com.helio.tasks.functional.page.TasksPage;

public class TasksTest extends BaseTest {
	@SuppressWarnings("unused")
	private DSL dsl;
	private TasksPage page;
	
	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("http://localhost:8001/tasks/");
		dsl = new DSL();
		page = new TasksPage();
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		//Clicar em Add Todo
		page.clicarNoBotaoAddTodo();
		page.aguardaCarregamentoDoBotaoSavePorId(30);
		
		//Escrever descrição
		page.escreveNoCampoTask("Teste Task 001");
		
		//Escrever a data
		page.escreveNoCampoDueDate("01/03/2024");
		
		//Clicar em salvar
		page.clicarNoBotaoSave();
		page.aguardaCarregamentoDaMensagemDeSucessoSuccess(30);
		
		//Validar mensagem de sucesso
		Assert.assertEquals("Success!", page.obterMensagemDeSucesso());
	}

	@Test
	public void naoDeveAdicionarTarefaSemDescricao() {
		page.clicarNoBotaoAddTodo();
		page.aguardaCarregamentoDoBotaoSavePorId(30);
		page.escreveNoCampoDueDate("03/03/2021");
		page.clicarNoBotaoSave();
		page.aguardaCarregamentoDaMensagemDeErroFillTheTaskDescription(30);
		Assert.assertEquals("Fill the task description", page.obterMensagemDeErro());
	}
	
	@Test
	public void naoDeveAdicionarTarefaSemData() {
		page.clicarNoBotaoAddTodo();
		page.aguardaCarregamentoDoBotaoSavePorId(30);
		page.escreveNoCampoTask("Teste Task 001");
		page.clicarNoBotaoSave();
		page.aguardaCarregamentoDaMensagemDeErroFillTheDueDate(30);
		Assert.assertEquals("Fill the due date", page.obterMensagemDeErro());
	}
	
	@Test
	public void naoDeveAdicionarTarefaComDataPassada() {
		page.clicarNoBotaoAddTodo();
		page.aguardaCarregamentoDoBotaoSavePorId(30);
		
		//Escrever descrição
		page.escreveNoCampoTask("Teste Task 001");
		
		//Escrever a data
		page.escreveNoCampoDueDate("03/03/2021");
		
		//Clicar em salvar
		page.clicarNoBotaoSave();
		page.aguardaCarregamentoDaMensagemDeErroDueDateMustNotBeInPast(30);
		
		//Validar mensagem de erro
		Assert.assertEquals("Due date must not be in past", page.obterMensagemDeErro());
	}
}