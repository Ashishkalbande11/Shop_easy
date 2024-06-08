package com.example.SHOP.easy.Entity;

import com.example.SHOP.easy.DTO.FAQDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String question;

    String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Product product;

    public FAQDTO getFAQDTO() {
        FAQDTO faqdto = new FAQDTO();

        faqdto.setId(id);
        faqdto.setQuestions(question);
        faqdto.setAnswers(answer);
        faqdto.setProductId(product.getId());

        return faqdto;
    }
}
