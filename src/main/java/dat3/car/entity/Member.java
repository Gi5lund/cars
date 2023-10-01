package dat3.car.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
//-----
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")


public class Member extends UserWithRoles
	{
	//@Id
//	//@GeneratedValue(strategy = GenerationType.UUID)
//	@Column(name = "username",unique = true)
//		private String username;
//	@Column(name="password",nullable = false)
//		private String password;
//	@Column(name = "e-mail")
//		private String email;
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
		@OneToMany(mappedBy = "member",cascade = CascadeType.DETACH, orphanRemoval = true)
		private List<Reservation> reservations;



		public Member(String user, String password, String email, String firstName,
						  String lastName, String street, String city, String zip) {
			super(user,password,email);
			this.firstName = firstName;
			this.lastName = lastName;
			this.street = street;
			this.city = city;
			this.zip = zip;
		}
		public void addReservation(Reservation reservation){
			if(reservations==null){
				reservations=new ArrayList<>();
			}
			reservations.add(reservation);
		}
	}
