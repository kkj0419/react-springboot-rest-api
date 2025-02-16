package com.example.gccoffe.repository;

import com.example.gccoffe.model.Category;
import com.example.gccoffe.model.Product;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.distribution.Version;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.MysqldConfig.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class ProductJdbcRepositoryTest {

    static EmbeddedMysql embeddedMysql;

    @BeforeAll
    static void setup() {
        var config = aMysqldConfig(Version.v8_0_11)
                .withCharset(Charset.UTF8)
                .withPort(2215)
                .withUser("test", "test1234!")
                .withTimeZone("Asia/Seoul")
                .build();
        embeddedMysql = anEmbeddedMysql(config)
                .addSchema("test-order_mgmt", ScriptResolver.classPathScript("schema.sql"))
                .start();
    }

    @AfterAll
    static void cleanup() {
        embeddedMysql.stop();
    }

    @Autowired
    ProductRepository repository;
    private final Product newProduct = new Product(UUID.randomUUID(), "new-product", Category.COFFEE_BEAN_PACKAGE, 1000L);


    @Test
    @Order(1)
    @DisplayName("Product add")
    void testInsert() {

        //given
        repository.insert(newProduct);
        //when
        var all = repository.findAll();
        //then
        assertThat(all.isEmpty(), is(false));
    }


    @Test
    @Order(2)
    @DisplayName("상품을 이름으로 조회할 수 있다")
    void testFindByName() {

        //given
        var findOne = repository.findByName(newProduct.getProductName());

        assertThat(findOne.isEmpty(), is(false));
    }

    @Test
    @Order(3)
    @DisplayName("상품을 아이디로 조회할 수 있다")
    void testFindById() {

        //given
        var findOne = repository.findById(newProduct.getProductId());

        assertThat(findOne.isEmpty(), is(false));
    }

    @Test
    @Order(4)
    @DisplayName("상품을 카테고리로 조회할 수 있다")
    void testFindByCategory() {

        //given
        var findOne = repository.findByCategory(Category.COFFEE_BEAN_PACKAGE);

        assertThat(findOne.isEmpty(), is(false));

    }

    @Test
    @Order(5)
    @DisplayName("상품을 수정할 수 있다")
    void testUpdateProduct(){

        //given
        newProduct.setProductName("update-product");
        repository.update(newProduct);

        //when
        var findOne = repository.findById(newProduct.getProductId());

        //then
        assertThat(findOne.isEmpty(), is(false));
        assertThat(findOne.get(), samePropertyValuesAs(newProduct));

    }


    @Test
    @Order(6)
    @DisplayName("상품을 전체 삭제한다")
    void testDeleteAll(){

        repository.deleteAll();
        var all = repository.findAll();
        assertThat(all.isEmpty(),is(true));
    }
}