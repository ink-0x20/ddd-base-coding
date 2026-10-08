package com.inkblogdb.ddd.infrastructure.config;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Component
public class PlayerIdInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
    @SuppressWarnings("unchecked")
    Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(
        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE
    );
    if (pathVariables == null || !pathVariables.containsKey("playerId")) {
      return true;
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }

    String pathPlayerId = pathVariables.get("playerId");
    PlayerId authPlayerId = (PlayerId) authentication.getPrincipal();
    if (authPlayerId == null || !authPlayerId.value().equals(pathPlayerId)) {
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
      return false;
    }

    return true;
  }

}
