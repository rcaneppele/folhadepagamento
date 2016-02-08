package br.com.rcaneppele.folhadepagamento.funcionario;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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
	
	public Funcionario buscaPorCPF(String cpf) {
		String jpql = "SELECT f FROM " +Funcionario.class.getName() + " f WHERE f.dadosPessoais.cpf = :cpf";
		try {
			return em.createQuery(jpql, Funcionario.class)
					.setParameter("cpf", cpf)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Funcionario buscaPorMatricula(String matricula) {
		String jpql = "SELECT f FROM " +Funcionario.class.getName() + " f WHERE f.dadosProfissionais.matricula = :matricula";
		try {
			return em.createQuery(jpql, Funcionario.class)
					.setParameter("matricula", matricula)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
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
