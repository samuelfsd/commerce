package com.samuelfsd.br.commerce.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductRequestDTO {
    private Long id;

    @Size(min = 3, max = 80, message = "O nome deve ter entre 3 a 100 caracteres.")
    @NotBlank(message = "O campo nome é requerido.")
    private String name;

    @Size(min = 10, message = "A descrição não deve ter menos que 10 caracteres.")
    private String description;

    @Positive(message = "O preço deve ser positivo.")
    private Double price;

    private String imgUrl;


    public ProductRequestDTO(){}

    public ProductRequestDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
