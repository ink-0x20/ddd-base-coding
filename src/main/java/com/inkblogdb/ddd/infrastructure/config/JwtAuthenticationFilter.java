package com.inkblogdb.ddd.infrastructure.config;

import com.inkblogdb.ddd.domain.model.authentication.AuthenticationRepository;
import com.inkblogdb.ddd.domain.model.authentication.AuthenticationToken;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final AuthenticationRepository authenticationRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    AuthenticationToken jwt = new AuthenticationToken(authHeader.substring(7));
    if (!authenticationRepository.validateToken(jwt)) {
      filterChain.doFilter(request, response);
      return;
    }

    PlayerId playerId = authenticationRepository.extractPlayerId(jwt);
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        playerId, null, Collections.emptyList()
    );
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }

}
