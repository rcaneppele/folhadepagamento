package br.com.rcaneppele.folhadepagamento.funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	
	public Funcionario buscaPorId(Long id) {
		return em.find(Funcionario.class, id);
	}
	
	public Funcionario carregaFuncionarioComReajustes(Long id) {
		String jpql = "SELECT f FROM " +Funcionario.class.getName() + " f LEFT JOIN FETCH f.reajustes WHERE f.id = :id";
		return em.createQuery(jpql, Funcionario.class)
				.setParameter("id", id)
				.getSingleResult();
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
	
	public BigDecimal buscaSalarioAtualDoFuncionario(Funcionario funcionario) {
		String jpql = "SELECT f.dadosProfissionais.salario FROM " +Funcionario.class.getName() + " f WHERE f = :funcionario";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("funcionario", funcionario)
				.getSingleResult();
	}
	
	public List<Funcionario> buscaTodosQueForamContratadosAntesDe(LocalDate data) {
		String jpql = "SELECT f FROM " +Funcionario.class.getName() + " f WHERE f.dadosProfissionais.dataAdmissao <= :data ORDER BY f.dadosPessoais.nome";
		return em.createQuery(jpql, Funcionario.class)
				.setParameter("data", data)
				.getResultList();
	}

}
