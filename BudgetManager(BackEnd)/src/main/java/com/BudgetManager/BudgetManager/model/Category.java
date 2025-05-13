package com.BudgetManager.BudgetManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double limitAmount;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Transaction> transactions= new ArrayList<>();

}
