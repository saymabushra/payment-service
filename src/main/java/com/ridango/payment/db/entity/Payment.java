package com.ridango.payment.db.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Payment.TABLE_NAME)
public class Payment {

  static final String TABLE_NAME = "payment";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "timestamp", nullable = false, updatable = false)
  @CreationTimestamp
  private Date timestamp;

  @Column(name = "sender_account_id")
  private Long senderAccountId;

  @Column(name = "receiver_account_id")
  private Long receiverAccountId;

  @Column(name = "amount")
  private BigDecimal amount;

}
