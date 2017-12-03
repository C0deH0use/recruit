package com.code.house.recruit.common.nosql.repos;


import com.code.house.recruit.common.nosql.documents.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;

import static com.code.house.recruit.common.nosql.documents.User.builder;

@DataMongoTest
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepoTest {
    @Autowired
    private ReactiveMongoTemplate template;

    @Autowired
    private UserRepo repo;

    @Before
    public void setUp() throws Exception {
        template.collectionExists(User.class)
                .flatMap(exist -> exist ? template.dropCollection(User.class) : Mono.just(exist))
                .flatMap(o -> template.createCollection(User.class, CollectionOptions.empty().size(1024 * 1024).maxDocuments(100).capped()))
                .then()
                .block();
        repo.saveAll(
                Flux.just(builder()
                                .email("info@code-house.pl")
                                .firstName("Mms")
                                .lastName("YouKnowHwo")
                                .status(User.Status.ACTIVE)
                                .build(),
                        builder()
                                .email("Aga@poczta.pl")
                                .firstName("Aga")
                                .lastName("Konopka")
                                .status(User.Status.ACTIVE)
                                .build()))
                .then()
                .block();

    }

    @Test
    public void test1() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        repo.count() //
                .doOnNext(System.out::println) //
                .thenMany(repo.saveAll(
                        Flux.just(
                                builder().firstName("Hank").lastName("Schrader").email("test@email.com").build(),
                                builder().firstName("Mike").lastName("Ehrmantraut").email("test2@email.com").build()
                        )
                ))//
                .last() //
                .flatMap(v -> repo.count()) //
                .doOnNext(System.out::println) //
                .doOnSuccess(it -> countDownLatch.countDown()) //
                .doOnError(throwable -> countDownLatch.countDown()) //
                .subscribe();

        countDownLatch.await();
    }

}
