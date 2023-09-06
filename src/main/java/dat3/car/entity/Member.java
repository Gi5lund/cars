package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
//-----
@Entity
@Table(name = "member")

public class Member extends AdminDetails
	{
	@Id
	//@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "username",unique = true)
		private String username;
	@Column(name="password",nullable = false)
		private String password;
	@Column(name = "e-mail")
		private String email;
	@NotNull
	@Column(length = 60)
		private String firstName;
		@NotNull
		@Column(length = 60)
		private String lastName;
		@NotNull
		@Column(length = 60)
		private String street;
		@Column(length = 60)
		private String city;
		@Column(length = 60)
		private String zip;
		@Column(name = "approved")
		private boolean approved;
		@Column(name = "ranking")
		private int ranking;



		public Member(String user, String password, String email, String firstName,
						  String lastName, String street, String city, String zip) {
			this.username = user;
			this.password= password;
			this.email = email;
			this.firstName = firstName;
			this.lastName = lastName;
			this.street = street;
			this.city = city;
			this.zip = zip;
		}

	}
