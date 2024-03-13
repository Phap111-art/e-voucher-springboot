package com.example.evoucherproject.ultil;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class RequestUtils {
    public static String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return "token_null";
    }
}
