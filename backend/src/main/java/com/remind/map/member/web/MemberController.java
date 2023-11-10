package com.remind.map.member.web;


import com.remind.map.member.domain.KakaoTokenDto;
import com.remind.map.member.domain.Member;
import com.remind.map.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/kakao")
    public void kakaoLogin(@RequestParam("code") String code) {
//        String token =

        KakaoTokenDto kakaoAccessToken = memberService.getKakaoAccessToken(code);

        Member member = memberService.getMemberInfo(kakaoAccessToken.getAccess_token());


    }


}
