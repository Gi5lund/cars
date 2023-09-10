package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service

public class MemberService
	{
		MemberRepository memberRepository;
		public MemberService(MemberRepository memberRepository){
			this.memberRepository=memberRepository;
		}

		public List<MemberResponse> getMembers(boolean includeAll)
			{

				List<Member> members=memberRepository.findAll();

				List<MemberResponse> respons=members.stream().map((member -> new MemberResponse(member,includeAll))).toList();

				return respons;

			}
			//Find all members who have made a reservation
		public List<MemberResponse> membersWithReservation(){
			List<Member> members=memberRepository.findAllByReservationsIsNotNull();
			List<MemberResponse> response=members.stream().map((member -> new MemberResponse(member,false))).toList();
			return response;
		}

		public MemberResponse addMember(MemberRequest body)
			{
				Member newMember= MemberRequest.getMemberEntity(body);
				if(memberRepository.existsById(body.getUsername())){
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This user already exists");
				}
				newMember=memberRepository.save(newMember);
				return new MemberResponse(newMember,true);
			}
		public ResponseEntity<Boolean> editMember(MemberRequest body, String username) {
			Member member = getMember(username);
			if(!body.getUsername().equals(username)){
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change username");
			}
			member.setPassword(body.getPassword());
			member.setEmail(body.getEmail());
			member.setFirstName(body.getFirstName());
			member.setLastName(body.getLastName());
			member.setStreet(body.getStreet());
			member.setCity(body.getCity());
			member.setZip(body.getZip());
			memberRepository.save(member);
			return ResponseEntity.ok(true);
		}

		public MemberResponse findById(String username) {
			Member member = getMember(username);
			return new MemberResponse(member,true);
		}

		public ResponseEntity<Boolean> setRanking(String username, int value)
			{
				Member member=getMember(username);
				member.setRanking(value);
				memberRepository.save(member);
				return ResponseEntity.ok(true);
			}


		public void deleteMemberByID(String username)
			{
				memberRepository.delete(getMember(username));

			}

			//utility metode:
			private Member getMember(String username){
				Member member;
				member= memberRepository.findById(username).
						orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Member with this username does not exist"));
				return member;
			}



	}
