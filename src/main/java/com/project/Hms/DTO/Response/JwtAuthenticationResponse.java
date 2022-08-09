package com.project.Hms.DTO.Response;
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String UserNameOrEmail;
    private Object role;

    public JwtAuthenticationResponse(String accessToken, String UserNameOrEmail , Object role) {
        this.accessToken = accessToken;
        this.UserNameOrEmail = UserNameOrEmail;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;

    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUserNameOrEmail() {
        return UserNameOrEmail;
    }

    public void setUserNameOrEmail(String userNameOrEmail) {
        this.UserNameOrEmail = userNameOrEmail;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }
}
