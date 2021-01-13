package by.finalproject.controller.request.customer;

import by.finalproject.domain.Gender;
import lombok.Data;

@Data
public class CustomerCreate {

  private String name;

  private String surname;

  private String login;

  private String password;

  private String phone;

  private String address;

  private Gender gender;
}
