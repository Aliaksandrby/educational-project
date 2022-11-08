package by.carlab.pojo;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "t_car")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_car")
    private String nameCar;

    @Column(name = "type_of_body")
    private String typeOfBody;

    @Column(name = "type_engine")
    private String typeEngine;

    @Column(name = "type_transmission")
    private String typeTransmission;

    @Column(name = "year_of_issue")
    private int yearOfIssue;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private double price;

    public Car(Integer id, String nameCar, String typeOfBody, String typeEngine, String typeTransmission, int yearOfIssue, String image, double price) {
        this.id = id;
        this.nameCar = nameCar;
        this.typeOfBody = typeOfBody;
        this.typeEngine = typeEngine;
        this.typeTransmission = typeTransmission;
        this.yearOfIssue = yearOfIssue;
        this.image = image;
        this.price = price;
    }

    public Car(String nameCar, String typeOfBody, String typeEngine, String typeTransmission, int yearOfIssue, String image, double price) {
        this.nameCar = nameCar;
        this.typeOfBody = typeOfBody;
        this.typeEngine = typeEngine;
        this.typeTransmission = typeTransmission;
        this.yearOfIssue = yearOfIssue;
        this.image = image;
        this.price = price;
    }
}