package dev.cupokki.ChatKiosk.common.tool;

import dev.cupokki.ChatKiosk.entity.Product;
import dev.cupokki.ChatKiosk.repository.ProductRepository;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductQueryTool {

    private final ProductRepository productRepository;

    @Tool("Get Product information(Kind of price) by keyword(Product name). Information contain price and currency")
    public Optional<Product> findProductByKeyword(@P("Product keyword from message ") String keyword) {
        log.info("find={}", keyword);
        var product= productRepository.findByName(keyword);
//                .orElseThrow(() -> new RuntimeException()); // 흐름이 이상한가? tool에서 예외 던지는 것 보다 아예 여기서 처리해야하나
        log.info("founded={}", product.get());
        return product;
    }

    @Tool("""
            현재시간을 반환합니다.
            """)
    public String getCurrentTime() {
        var time = LocalDateTime.now().toLocalTime().toString();
        log.info("Time{}",  time);
        return time;
    }


//    @Tool("모든 상품의 개수를 조회합니다.")
//    public String getProductCount(String keyword) {
////        List<Product> products = productRepository.findAll();
//        log.info("count");
//        return String.valueOf(3);
//    }

//    @Tool("적절한 도구가 없다면 사용하세요")
//    public String unknown() {
////        List<Product> products = productRepository.findAll();
//        log.info("not found");
//        return "뭐라고요?";
//    }

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
