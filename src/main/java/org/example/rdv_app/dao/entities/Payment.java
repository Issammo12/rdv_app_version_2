package org.example.rdv_app.dao.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Builder
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EVENT_ID")
    private String event_id;

    @Column(name = "TRANSACTION_ID")
    private String transaction_id;

    @Column(name = "EVENT_NAME")
    private String event_name;

    @Column(name = "PAYMENT_STATUS")
    private String payment_status;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "TRANSACTION_CREATED_AT")
    private String transaction_created_at;

    @Column(name = "ORDER_ID")
    private String order_id;

    @Column(name = "USER_MAIL")
    private String user_mail;


    @Column(nullable = true)
    private String format_document;


    @Column(name = "PRODUCT_REFERENCE")
    private String product_reference;

    @Column(name = "IP_ADRESS")
    private String ip_adress;


    @Column(name = "APPLICATION_SOURCE")
    private String applicationSource;


    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk_payments_detail")
    private PaymentDetails detail;

    @Column(name = "created_on")
    @CreationTimestamp
    private Date createdOn;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private Date updatedOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "TREATMENT_STATUS")
    private PaymentStatus treatmentStatus;

}
