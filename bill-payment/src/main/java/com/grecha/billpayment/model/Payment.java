package com.grecha.billpayment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PAYMENT")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = "createdAt",
        allowGetters = true)
public class Payment{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;


    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;


    //!!!!!!!!!!!!
    /*@Column(name = "CUSTOMER", nullable = false)
    private String customer;
    */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "PAYMENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "BILLER_id")}
    )
    private Set<Biller> billers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "PAYMENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CUSTOMER_id")}
    )
    private Set<Customer> customers;

    @Column(name = "ACCOUNT", nullable = false)
    private int account;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;

    public Payment(int account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Biller> getBillers() {
        return billers;
    }

    public void setBillers(Set<Biller> billers) {
        this.billers = billers;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Payment(){
    }




    /*Выводим информацию по всем платежам
    @Override
    public String toString() {
        return String.format("ID: %s | поленейм: %s | поленейм: %s | поленейм: %s",
                this.date, this.customer, this.biller, this.account, this.amount);
    }
    */
}