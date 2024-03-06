package com.yamuna.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Customer")
public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String customerName;
        private String emailAddress;
        private String password;
        private String phone;
        private String submittingTransferAgent;
        private String cusip;
        private String inputForm;
        private String cusipType;
        private String issuerName;
        private String issueDescription;
        private String effectiveDate;
        private String transferAgentName;
        private String transferAgentNumber;
        private String transferAgentAddress;
        private String fins;


    }

