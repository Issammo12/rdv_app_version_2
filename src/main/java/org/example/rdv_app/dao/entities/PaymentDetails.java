package org.example.rdv_app.dao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "payments_details")
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "JSON")
    private String event;

    @JsonBackReference
    @OneToOne(mappedBy = "detail",cascade = CascadeType.ALL)
    private Payment payment;

    @Column(name = "created_on")
    @CreationTimestamp
    private Date createdOn;
}
