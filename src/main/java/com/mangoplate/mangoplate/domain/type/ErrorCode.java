package com.mangoplate.mangoplate.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //토큰 만료시
    TOKEN_TIME_END(HttpStatus.REQUEST_TIMEOUT, "토큰시간이 만료되었습니다. 재로그인 해주세요."),
    //로그인
    LOGIN_NO_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 틀렸습니다."),
    LOGIN_NO_JOIN(HttpStatus.UNAUTHORIZED,"가입되지 않은 아이디입니다."),
    //회원가입
    REGISTER_SAME_ID(HttpStatus.UNAUTHORIZED,"동일한 아이디가 존재합니다."),

    //게시글
    NO_POST(HttpStatus.UNAUTHORIZED,"게시글이 없습니다."),
    NO_POSTCODE(HttpStatus.UNAUTHORIZED,"게시글 코드를 입력해주세요"),
    NO_SHOP_ADDRESS(HttpStatus.UNAUTHORIZED,"가게 주소를 입력해주세요"),
    NO_SHOP_NAME(HttpStatus.UNAUTHORIZED,"가게 이름을 입력해주세요"),
    NO_CATEGORY(HttpStatus.UNAUTHORIZED,"음식 종류를 입력해주세요"),
    NO_MENU(HttpStatus.UNAUTHORIZED,"메뉴를 입력해주세요"),
    NO_PRICE(HttpStatus.UNAUTHORIZED,"가격대를 입력해주세요"),

    //리뷰
    NO_REVIEW(HttpStatus.UNAUTHORIZED,"리뷰가 없습니다."),
    NO_REVIEWCODE(HttpStatus.UNAUTHORIZED,"리뷰코드를 입력해주세요."),
    SHORT_CONTENT(HttpStatus.UNAUTHORIZED,"내용을 20자 이상 입력해주세요."),
    NO_TASTE(HttpStatus.UNAUTHORIZED,"맛 평가를 선택해주세요."),

    //이메일 인증
    EMAIL_NO_SUCCESS(HttpStatus.UNAUTHORIZED,"인증번호가 맞지 않습니다."),

    EMAIL_FAILE(HttpStatus.FAILED_DEPENDENCY,"이메일 전송에 실패하였습니다.")

    ;

    private final HttpStatus status;
    private final String message;
}
