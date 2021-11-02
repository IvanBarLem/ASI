package es.udc.asiproject.persistence.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 60)
	private String name;
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Pack> packs;

	public Product() {
	}

	public Product(String name) {
		this.name = name;
	}

	public Product(Long id, String name, Set<Pack> packs) {
		this.id = id;
		this.name = name;
		this.packs = packs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Pack> getPacks() {
		return packs;
	}

	public void setPacks(Set<Pack> packs) {
		this.packs = packs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, packs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(packs, other.packs);
	}
}
