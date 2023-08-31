package dat3.car.api;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.repository.MemberRepository;
import dat3.car.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
class MemberController {
	MemberService memberService;

	public MemberController(MemberService memberService)
		{
			this.memberService = memberService;
		}

	//Security Admin Only
	@GetMapping
	List<MemberResponse> getMembers(){ return memberService.getMembers(false);}

	//Security Admin
	@GetMapping(path = "/{username}")
	MemberResponse getMemberById(@PathVariable String username) throws Exception {
		return memberService.findById(username);
	}

	//Security --> Anonymous
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	MemberResponse addMember(@RequestBody MemberRequest body){
		return memberService.addMember(body);
	}

	//Security ???
	@PutMapping("/{username}")
	ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
		return memberService.editMember(body,username);
	}

	//Security ????
	@PatchMapping("/ranking/{username}/{value}")
	ResponseEntity<Boolean> setRankingForUser(@PathVariable String username, @PathVariable int value) {
		return memberService.setRanking(username,value);
	}

	// Security ????
	@DeleteMapping("/{username}")
	ResponseEntity<Boolean> deleteMemberByUsername(@PathVariable String username) {
		return memberService.deleteMemberByID(username);
	}

}