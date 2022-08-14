package com.project.Hms.DTO.Response;
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String UserNameOrEmail;
    private Object role;
    private Object profile;

    public JwtAuthenticationResponse(String accessToken, String UserNameOrEmail , Object role,Object profile) {
        this.accessToken = accessToken;
        this.UserNameOrEmail = UserNameOrEmail;
        this.role = role;
        this.profile= profile;
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

    public Object getProfile() {
        return profile;
    }

    public void setProfile(Object profile) {
        this.profile = profile;
    }
}
