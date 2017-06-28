package com.opaulochaves.auginc.core.security.controller;

import com.opaulochaves.auginc.core.security.AuthService;
import com.opaulochaves.auginc.core.security.EmployeeDetails;
import com.opaulochaves.auginc.web.dto.AuthResponseDTO;
import com.opaulochaves.auginc.web.dto.LoginRequestDTO;
import com.opaulochaves.auginc.web.util.JwtTokenUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

    @Autowired
    private AuthService authService;

    @Value("${auginc.jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Ponto de entrada para a autenticação do usuário no invocado no submit do
     * form login
     *
     * @param loginRequest
     * @param request
     * @param response
     * @param device
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestDTO loginRequest,
            HttpServletRequest request, HttpServletResponse response, Device device) throws AuthenticationException {

        EmployeeDetails details = authService.authenticate(request, response, loginRequest, device);
        final String token = jwtTokenUtil.generateToken(details, device);

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @RequestMapping(value = "/auth/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String refreshedToken = authService.refreshToken(request);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(new AuthResponseDTO(refreshedToken));
    }
}
