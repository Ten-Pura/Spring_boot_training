package com.example.samplewebfluxapp.restcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import com.example.samplewebfluxapp.entity.Post;
import com.example.samplewebfluxapp.repository.PostRepository;

import jakarta.annotation.PostConstruct;

@RestController
public class SampleRestController {

    private final WebClient webClient;

    public SampleRestController(WebClient.Builder builder) {
        super();
        webClient = builder.baseUrl("jsonplaceholder.typicode.com").build();   
    }

    @Autowired
    private PostRepository PostRepository;

    @RequestMapping("/")
    public String index() {
        return "Hello Flux!";
    }

    @RequestMapping("/web/{id}")
    public Mono<Post> web(@PathVariable int id) {
        return this.webClient.get()
            .uri("/posts/" + id)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Post.class);
    }

    @RequestMapping("/web")
    public Flux<Post> webAll() {
        return this.webClient.get()
            .uri("/posts")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(Post.class);
    }

    @RequestMapping("/webpost/{id}")
    public Mono<Post> webPost(@PathVariable int id) {
        Post post = PostRepository.findById(id);
        return this.webClient.post()
            .uri("/posts")
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(post)
            .retrieve()
            .bodyToMono(Post.class);
    }
    
    @RequestMapping("/post/{id}")
    public Mono<Post> getPostById(@PathVariable int id) {
        Post post = PostRepository.findById(id);
        return Mono.just(post);
    }

    @RequestMapping("/posts")
    public Flux<Object> getAllPosts() {
       return Flux.fromArray(PostRepository.findAll().toArray());
    }

    // @RequestMapping("/file")
    // public Mono<String> file() {
    //     String result = "";
    //     try {
    //         ClassPathResource cr = new ClassPathResource("sample.txt");
    //         InputStream is = cr.getInputStream();
    //         InputStreamReader isr = new InputStreamReader(is, "utf-8");
    //         BufferedReader br = new BufferedReader(isr);
    //         String line;
    //         while ((line = br.readLine()) != null) {
    //             result += line;
    //         }
    //     } catch (IOException e) {
    //         result = e.toString();
    //     }

    //     return Mono.just(result);
    // }

    @PostConstruct
    public void init() {
        Post p1 = new Post(1, "Hello", "Hello World!");
        Post p2 = new Post(2, "Morning", "rice and fish");
        Post p3 = new Post(3, "Juice", "Mango Juice");
        Post p4 = new Post(4, "Job", "System Engineer");
        PostRepository.saveAndFlush(p1);
        PostRepository.saveAndFlush(p2);
        PostRepository.saveAndFlush(p3);
        PostRepository.saveAndFlush(p4);
    }
}
