package by.finalproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
  PROCESSING(1),
  CONFIRMED(1),
  COMPLETED(2),
  CANCELLED(3),
  UNKNOWN(4);

  private final Integer id;

  public static OrderStatus findById(int searchId) {
    for (OrderStatus status : OrderStatus.values()) {
      if (status.getId() == searchId) {
        return status;
      }
    }
    return OrderStatus.UNKNOWN;
  }
}
