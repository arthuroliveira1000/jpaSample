package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {

	// EntityManagers podem ser criado ou podem ser obtidos de um
	// EntityManagerFactory.
	// Leia mais em: Introdução ao EntityManager
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

		// EntityManager é o serviço central para todas as ações de
		// persistência. Entidades são objetos de Java claros que são alocados
		// como qualquer outro objeto Java. Eles não ficam persistentes
		// explicitamente até seu código interagir com o EntityManager para os
		// fazer persistente. O EntityManager administra o O/R que o mapea entre
		// uma classe de entidade e uma fonte de dados subjacente. O
		// EntityManager provê APIs para criar consultas, buscando objetos,
		// sincronizando objetos, e inserindo objetos no banco de dados
		//
		// Leia mais em: Introdução ao EntityManager
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
