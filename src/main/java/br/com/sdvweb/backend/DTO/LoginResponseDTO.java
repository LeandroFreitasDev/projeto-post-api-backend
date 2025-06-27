package br.com.sdvweb.backend.DTO;

public class LoginResponseDTO {
	private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}


