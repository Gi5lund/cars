package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
//-----
@Entity
@Table

public class Member
	{
	@Id
	//@GeneratedValue(strategy = GenerationType.UUID)
		private String username;
		private String password;
		private String email;
		private String firstName;
		private String lastName;
		private String street;
		private String city;
		private String zip;
		private boolean approved;
		private int ranking;
		@UpdateTimestamp
		private LocalDateTime lastEdited;
		@CreationTimestamp
		private LocalDateTime created;


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