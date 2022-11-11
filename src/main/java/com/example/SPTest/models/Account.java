package com.example.SPTest.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long id;

    @JsonBackReference(value="meters")
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Meter> meters = new ArrayList<>();

    public void addMeter(Meter meter){
        this.meters.add(meter);
    }

}
