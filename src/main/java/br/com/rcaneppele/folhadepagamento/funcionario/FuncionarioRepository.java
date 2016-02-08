package br.com.rcaneppele.folhadepagamento.funcionario;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@RequestScoped
public class FuncionarioRepository {

	private EntityManager em;
	
	/**
	 * CDI Eyes Only!
	 */
	protected FuncionarioRepository() {
	}

	@Inject
	public FuncionarioRepository(EntityManager em) {
		this.em = em;
	}
	
	public Funcionario buscaPorId(Long id) {
		return em.find(Funcionario.class, id);
	}
	
	public List<Funcionario> buscaTodosOrdenadosPeloNome() {
		String jpql = "SELECT f FROM " +Funcionario.class.getName() + " f ORDER BY f.dadosPessoais.nome";
		return em.createQuery(jpql, Funcionario.class).getResultList();
	}
	
	public List<Funcionario> buscaPorCPFOuMatricula(String cpf, String matricula) {
		String jpql = "SELECT f FROM " +Funcionario.class.getName() + " f WHERE f.dadosPessoais.cpf = :cpf OR f.dadosProfissionais.matricula = :matricula";
		return em.createQuery(jpql, Funcionario.class)
				.setParameter("cpf", cpf)
				.setParameter("matricula", matricula)
				.getResultList();
	}
	
	public void cadastra(Funcionario novo) {
		this.em.joinTransaction();
		this.em.persist(novo);
	}
	
	public void atualiza(Funcionario existente) {
		this.em.joinTransaction();
		this.em.merge(existente);
	}
	
	public void remove(Funcionario removido) {
		this.em.joinTransaction();
		removido = buscaPorId(removido.getId());
		this.em.remove(removido);
	}

}
