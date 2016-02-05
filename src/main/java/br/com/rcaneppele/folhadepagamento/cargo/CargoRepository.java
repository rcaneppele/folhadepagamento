package br.com.rcaneppele.folhadepagamento.cargo;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class CargoRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Cargo buscaPorId(Long id) {
		return em.find(Cargo.class, id);
	}
	
	public List<Cargo> buscaTodos() {
		String jpql = "SELECT c FROM " +Cargo.class.getName() + " c";
		return em.createQuery(jpql, Cargo.class).getResultList();
	}
	
	@Transactional
	public void cadastra(Cargo novo) {
		this.em.persist(novo);
	}
	
	@Transactional
	public void atualiza(Cargo existente) {
		this.em.merge(existente);
	}
	
	@Transactional
	public void remove(Cargo removido) {
		removido = em.getReference(Cargo.class, removido.getId());
		this.em.remove(removido);
	}
	
}
