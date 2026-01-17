package com.vaultcore.vaultcore.fraud;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.math.BigDecimal;

@Component
public class FraudDetectionInterceptor implements HandlerInterceptor {

    @Value("${vaultcore.fraud.threshold}")
    private BigDecimal fraudThreshold;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // Intercept only transfer API
        if (request.getRequestURI().contains("/api/ledger/transfer")) {

            String amountParam = request.getParameter("amount");

            if (amountParam != null) {
                BigDecimal amount = new BigDecimal(amountParam);

                if (amount.compareTo(fraudThreshold) > 0) {
                    // Simulated 2FA trigger
                    System.out.println(
                        "⚠ FRAUD ALERT: High-value transaction detected. 2FA triggered."
                    );
                }
            }
        }
        return true;
    }
}


