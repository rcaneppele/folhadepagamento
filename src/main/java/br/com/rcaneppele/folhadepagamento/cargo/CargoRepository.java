package br.com.rcaneppele.folhadepagamento.cargo;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Named
@RequestScoped
public class CargoRepository {

	private EntityManager em;
	
	/**
	 * CDI Eyes Only!
	 */
	protected CargoRepository() {
	}

	@Inject
	public CargoRepository(EntityManager em) {
		this.em = em;
	}
	
	public Cargo buscaPorId(Long id) {
		return em.find(Cargo.class, id);
	}
	
	public List<Cargo> buscaTodosOrdenadosPeloNome() {
		String jpql = "SELECT c FROM " +Cargo.class.getName() + " c ORDER BY c.nome";
		return em.createQuery(jpql, Cargo.class).getResultList();
	}
	
	public Cargo buscaPorNome(String nome) {
		String jpql = "SELECT c FROM " +Cargo.class.getName() + " c WHERE c.nome = :nome";
		try {
			return em.createQuery(jpql, Cargo.class)
					.setParameter("nome", nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void cadastra(Cargo novo) {
		this.em.joinTransaction();
		this.em.persist(novo);
	}
	
	public void atualiza(Cargo existente) {
		this.em.joinTransaction();
		this.em.merge(existente);
	}
	
	public void remove(Cargo removido) {
		this.em.joinTransaction();
		removido = buscaPorId(removido.getId());
		this.em.remove(removido);
	}
	
}
