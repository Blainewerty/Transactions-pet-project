package ru.milov.transactions.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "bills")
public class Bill {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private User user;

    private String name;

    private BigDecimal balance;

}
