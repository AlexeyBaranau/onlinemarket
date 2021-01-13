package by.finalproject.security.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

  private String token;
}
