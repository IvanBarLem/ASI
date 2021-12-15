package es.udc.asiproject.persistence.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import es.udc.asiproject.persistence.model.enums.RoleType;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 60)
	private String email;
	@Column(nullable = false, length = 60)
	private String password;
	@Column(nullable = false, length = 60)
	private String firstName;
	@Column(nullable = false, length = 60)
	private String lastName;
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private RoleType role;

	@OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
	private Set<Sale> agentSales = new HashSet<Sale>();
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Set<Sale> clientSales = new HashSet<Sale>();

	public User() {
	}

	public User(Builder builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.password = builder.password;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.role = builder.role;
	}

	public static Builder builder() {
		return new Builder();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public Set<Sale> getAgentSales() {
		return agentSales;
	}

	public Set<Sale> getClientSales() {
		return clientSales;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", role=" + role + "]";
	}

	public static class Builder {
		private Long id;
		private String email;
		private String password;
		private String firstName;
		private String lastName;
		private RoleType role;

		public Builder() {
		}

		public Builder id(Long id) {
			this.id = id;
			return Builder.this;
		}

		public Builder email(String email) {
			this.email = email;
			return Builder.this;
		}

		public Builder password(String password) {
			this.password = password;
			return Builder.this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return Builder.this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return Builder.this;
		}

		public Builder role(RoleType role) {
			this.role = role;
			return Builder.this;
		}

		public User build() {
			return new User(this);
		}
	}
}
