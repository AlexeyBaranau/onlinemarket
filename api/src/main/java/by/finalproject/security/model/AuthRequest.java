package by.finalproject.security.model;

import lombok.Data;

@Data
public class AuthRequest {
  private String login;

  private String password;
}
