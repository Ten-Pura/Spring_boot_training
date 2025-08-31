package com.example.samplewebfluxapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.samplewebfluxapp.entity.Post;
import com.example.samplewebfluxapp.repository.PostRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server
    .ServerResponse.ok;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.springframework.web.reactive.function.server
    .RouterFunctions.route;
import static org.springframework.web.reactive.function.server
    .RequestPredicates.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

@Controller
public class SampleController {

    @Autowired
    private PostRepository PostRepository;

    @RequestMapping("/f/flux")
    Mono<Rendering> flux() {
        return Mono.just(Rendering.view("flux")
            .modelAttribute("title", "Flux/Request Handler")
            .modelAttribute("msg", "これはリクエストハンドラのサンプルです。")
            .build());
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/f/hello"), this::hello)
            .andRoute(GET("/f/hello2"), this::hello2)
            .andRoute(GET("/f/flux2"), this::flux2);
    }

    Mono<ServerResponse> flux2(ServerRequest req) {
        Map map = new HashMap();
        map.put("title", "Flux/Function touting");
        map.put("msg", "これは関数ルーティングのサンプルです。");
        return ok().contentType(MediaType.TEXT_HTML).render("flux", map);
    }

    Mono<ServerResponse> hello(ServerRequest req) {
        return ok().body(Mono.just("Hello Functional routing world!"), String.class);
    }

    Mono<ServerResponse> hello2(ServerRequest req) {
        List<Post> list = null;
        list = PostRepository.findAll();
        return ok().body(Flux.fromArray(list.toArray()), Post.class);
    }

}
