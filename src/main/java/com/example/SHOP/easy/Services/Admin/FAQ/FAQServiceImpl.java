package com.example.SHOP.easy.Services.Admin.FAQ;

import com.example.SHOP.easy.DTO.FAQDTO;
import com.example.SHOP.easy.Entity.FAQ;
import com.example.SHOP.easy.Entity.Product;
import com.example.SHOP.easy.Repository.FAQRepository;
import com.example.SHOP.easy.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService{

    private final FAQRepository faqRepository;

    private final ProductRepository productRepository;

    public FAQDTO postFAQ(Long productId, FAQDTO faqdto){
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isPresent()){
            FAQ faq = new FAQ();

            faq.setQuestion(faqdto.getQuestions());
            faq.setAnswer(faqdto.getAnswers());
            faq.setProduct(optionalProduct.get());

            return faqRepository.save(faq).getFAQDTO();
        }
        return null;
    }

}
