package com.example.daollar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "children")
public class Children implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parentId", nullable = false)
    @JsonIgnore
    private Parent parent;

    private Double paidAmount;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer parentId;
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalAmount;
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sender;
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String receiver;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Parent getParent() { return parent; }
    public void setParent(Parent parent) { this.parent = parent; }

    public Double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(Double paidAmount) { this.paidAmount = paidAmount; }

    public Integer getParentId() { return parentId; }
    public void setParentId(Integer id) { this.parentId = id; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

}
