package com.pharmacy.management.pms.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medicine_group")
public class MedicineGroup extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "medicine_group")
    private List<Medicine> medicines;
}
