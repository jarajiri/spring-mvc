package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("new-form")
    public String newForm() {
        return "new-form";
    }

    // @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String members(@RequestParam String username,
                          @RequestParam int age,
                          Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String save(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }

}
