package com.BudgetManager.BudgetManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "budget",cascade = CascadeType.ALL)
    private List<Category> category = new ArrayList<>();

    private double limitAmount ;

    private double spintAmount;
}
