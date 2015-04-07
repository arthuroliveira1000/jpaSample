package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {

	// EntityManagers podem ser criado ou podem ser obtidos de um
	// EntityManagerFactory.
	// Leia mais em: Introdu��o ao EntityManager
	// http://www.devmedia.com.br/introducao-ao-entitymanager/5206#ixzz3Wae6EvtC
	private static EntityManagerFactory factory;

	
	public static void main(String[] args) {

		factory = Persistence.createEntityManagerFactory("jpa-example");

		Program p = new Program();
		p.addAluno("Guilherme");

		Aluno alunoCarregado = p.carregaAluno(1L);
		System.out.println("alunoCarregado = " + alunoCarregado.getNome());

		factory.close();
	}

	private Aluno carregaAluno(Long id) {

		// EntityManager � o servi�o central para todas as a��es de
		// persist�ncia. Entidades s�o objetos de Java claros que s�o alocados
		// como qualquer outro objeto Java. Eles n�o ficam persistentes
		// explicitamente at� seu c�digo interagir com o EntityManager para os
		// fazer persistente. O EntityManager administra o O/R que o mapea entre
		// uma classe de entidade e uma fonte de dados subjacente. O
		// EntityManager prov� APIs para criar consultas, buscando objetos,
		// sincronizando objetos, e inserindo objetos no banco de dados
		//
		// Leia mais em: Introdu��o ao EntityManager
		// http://www.devmedia.com.br/introducao-ao-entitymanager/5206#ixzz3WaVhXsbZ
		EntityManager manager = factory.createEntityManager();

		Aluno aluno = manager.find(Aluno.class, id);

		manager.close();

		return aluno;
	}

	private void addAluno(String nome) {
		Aluno aluno = new Aluno();
		aluno.setNome(nome);

		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		manager.persist(aluno);
		manager.getTransaction().commit();

		System.out.println("ID do aluno = " + aluno.getId());

		manager.close();
	}
}
