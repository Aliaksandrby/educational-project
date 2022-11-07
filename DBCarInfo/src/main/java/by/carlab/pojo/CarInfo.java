package by.carlab.pojo;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "t_car")
public class CarInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "type_body")
    private String typeBody;

    @Column(name = "class_auto")
    private String classAuto;

    @Column(name = "color")
    private String color;

    @Column(name = "engine_description")
    private String engineDescription;

    @Column(name = "price")
    private double price;

    @Column(name = "path_to_image")
    private String pathToImage;

    public CarInfo(String brand, String fullName, String typeBody, String classAuto,
                   String color, String engineDescription, double price, String pathToImage) {

        this.brand = brand;
        this.fullName = fullName;
        this.typeBody = typeBody;
        this.classAuto = classAuto;
        this.color = color;
        this.engineDescription = engineDescription;
        this.price = price;
        this.pathToImage = pathToImage;
    }

    public CarInfo(Integer id, String brand, String fullName, String typeBody, String classAuto,
                   String color, String engineDescription, double price, String pathToImage) {
        this.id = id;
        this.brand = brand;
        this.fullName = fullName;
        this.typeBody = typeBody;
        this.classAuto = classAuto;
        this.color = color;
        this.engineDescription = engineDescription;
        this.price = price;
        this.pathToImage = pathToImage;
    }
}