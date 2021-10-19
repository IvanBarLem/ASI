package es.udc.asiproject.backend.persistence.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Transport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 60)
	private String name;
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Pack> packs;

	public Transport() {
	}

	public Transport(String name) {
		this.name = name;
	}

	public Transport(Long id, String name, Set<Pack> packs) {
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
		Transport other = (Transport) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(packs, other.packs);
	}
}
