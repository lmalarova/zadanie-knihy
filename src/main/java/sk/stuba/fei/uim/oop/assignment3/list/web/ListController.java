package sk.stuba.fei.uim.oop.assignment3.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.list.logic.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.ListResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private ILendingListService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ListResponse> getAllLists() {
        return this.service.getAll().stream().map(ListResponse::new).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListResponse> addList() {
        return new ResponseEntity<>(new ListResponse(this.service.create()), HttpStatus.CREATED);
    }

}
