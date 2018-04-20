package com.grecha.billpayment.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "BILLER")
public class Biller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "billers")
    private Set<Payment> payments;

    public Biller(){
    }

    public Biller(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


  /*   Выводим информацию по всем billers
    @Override
    public String toString() {
        return String.format("name: %s",
                this.name);
    }
    */
}