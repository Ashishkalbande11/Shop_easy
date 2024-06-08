package com.example.SHOP.easy.Services.Admin.FAQ;

import com.example.SHOP.easy.DTO.FAQDTO;

public interface FAQService {

    FAQDTO postFAQ(Long productId, FAQDTO faqdto);
}
