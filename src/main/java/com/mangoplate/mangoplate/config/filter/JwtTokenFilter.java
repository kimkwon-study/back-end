package com.mangoplate.mangoplate.config.filter;

import com.mangoplate.mangoplate.domain.entity.User;
import com.mangoplate.mangoplate.domain.type.ErrorCode;
import com.mangoplate.mangoplate.exception.ApplicationException;
import com.mangoplate.mangoplate.service.UserService;
import com.mangoplate.mangoplate.utill.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.secret-key}")
    private String key;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            log.error("클라이언트 보내줄때 헤더에 Bearer 이 있는지 체크.");
            filterChain.doFilter(request, response);
            return;
        }
        try {
            final String token = header.split(" ")[1].trim();
            //TODO : get userId from token

            //TODO : check token is valid
            if (JwtTokenUtils.isExpired(token, key)) {
                log.error("토큰값이 맞지 않습니다.");
                filterChain.doFilter(request, response);
                return;
            }
            //TODO : 토큰에서 userID 가져오기.
            String userId = JwtTokenUtils.getUserId(token, key);

            //TODO : 만료가 되면 다시 토큰 발급 로그인 진행.
            User user = userService.loadUserByUserId(userId);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    //TODO :
                    user, null, List.of(new SimpleGrantedAuthority(user.getRole().toString()))
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (RuntimeException e) {
            log.error("Error occurs while validating. {}", e.toString());
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);

    }

}
