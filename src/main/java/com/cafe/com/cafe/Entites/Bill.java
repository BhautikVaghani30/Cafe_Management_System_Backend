package com.cafe.com.cafe.Entites;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;

// get bills for admin in desending order video - 10
@NamedQuery(name = "Bill.getAllBills", query = "select b from Bill b order by b.id desc")
// get bill for perticular user video - 10
@NamedQuery(name = "Bill.getBillByUserName", query = "select b from Bill b where b.createdBy=:username order by b.id desc")


// this entites crearted in video-9
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "bill")
public class Bill implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "contactnumber")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "paymentmethod")
    private String paymentMethod;

    @Column(name = "total")
    private Integer total;

    @Column(name = "productdetails", columnDefinition = "json")
    private String productDetail;

    @Column(name = "createdby")
    private String createdBy;
}
