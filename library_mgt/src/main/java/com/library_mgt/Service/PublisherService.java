package com.library_mgt.Service;

import com.library_mgt.Entity.Publisher;
import com.library_mgt.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> findAllPublishers(){
        return publisherRepository.findAll();
    }

    public Publisher findPublisherById(Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow(()->new RuntimeException("Publisher not Found"));
        return publisher;
    }

    public void createPublisher(Publisher publisher){
        publisherRepository.save(publisher);
    }
    public void updatePublisher(Publisher publisher){
        publisherRepository.save(publisher);
    }

    public void deletePublisherById(Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow(()->new RuntimeException("Publisher not Found"));
        publisherRepository.deleteById(publisher.getId());
    }
}
