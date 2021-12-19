package com.minlebay.models;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "carnumbers")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Character prefix;
    private int num;
    private Character suffix;
    private Character postfix;
    private String representation;

}
