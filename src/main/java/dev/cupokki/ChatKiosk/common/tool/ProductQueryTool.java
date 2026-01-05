package dev.cupokki.ChatKiosk.common.tool;

import dev.cupokki.ChatKiosk.entity.Product;
import dev.cupokki.ChatKiosk.repository.ProductRepository;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductQueryTool {

    private final ProductRepository productRepository;

    @Tool("키워드로 상품의 이름, 가격, 재고를 조회합니다.")
    public String findProductByKeyword(String keyword) {
//        List<Product> products = productRepository.findAll();
        var products = getDummyProducts(keyword);
        // TODO : pgvector 구현

        StringBuilder sb = new StringBuilder();
        sb.append("상품 목록: \n[");
        products.stream().limit(5).forEach(p -> {
            sb.append(String.format("id: %d, name: %s, price: %d, stock: %d",
                    p.getId(), p.getName(), 0, 0));
        });
        return sb.toString();

    }

    private List<Product> getDummyProducts(String keyword) {
        List<Product> dummyList = new ArrayList<>();

        // ⭐️ Builder 패턴을 사용하여 객체를 생성합니다.
        if (keyword.toLowerCase().contains("노트북")) {
            dummyList.add(Product.builder()
                    .id(101L)
                    .name("슬림핏 노트북 15")
                    .price(new BigDecimal("1500000"))
                    .stock(50)
                    .build());
            dummyList.add(Product.builder()
                    .id(102L)
                    .name("고성능 게이밍 노트북 X")
                    .price(new BigDecimal("2800000"))
                    .stock(15)
                    .build());
        } else if (keyword.toLowerCase().contains("키보드")) {
            dummyList.add(Product.builder()
                    .id(201L)
                    .name("무소음 무선 키보드")
                    .price(new BigDecimal("85000"))
                    .stock(120)
                    .build());
        } else {
            dummyList.add(Product.builder()
                    .id(301L)
                    .name("스마트폰 충전기 C타입")
                    .price(new BigDecimal("35000"))
                    .stock(300)
                    .build());
            dummyList.add(Product.builder()
                    .id(302L)
                    .name("대용량 보조 배터리")
                    .price(new BigDecimal("55000"))
                    .stock(80)
                    .build());
        }
        return dummyList;
    }
}
