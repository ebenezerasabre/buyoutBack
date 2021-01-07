package com.quest.buyout.service;

import com.quest.buyout.dao.HistoryRepository;
import com.quest.buyout.dao.ProductRepository;
import com.quest.buyout.model.History;
import com.quest.buyout.model.OutLaw;
import com.quest.buyout.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HistoryService {


    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    ProductRepository productRepository;

    private int increase = 5;

    public List<History> index(){ return historyRepository.findAll(); }

    public List<Product> findCustomerHistoryIndex(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);

        String customerId = body.get("customerId");
        ArrayList<Product> productArrayList = new ArrayList<>();

        int count = 0;
        for(int z = 0; z< historyRepository.count(); z++){
            if(historyRepository.findAll().get(z).getCustomerId().equals(customerId)){
                productArrayList.add(productRepository.findById(historyRepository
                        .findAll()
                        .get(z)
                        .getProductId())
                        .orElse(null));
                count += 1;
            }
        }
        if(count > 7){
            return productArrayList.subList(0, 7);
        }
        return productArrayList;
    }


    public ArrayList<Product> findCustomerHistory(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);

        String customerId = body.get("customerId");
        ArrayList<Product> productArrayList = new ArrayList<>();
        for(int z = 0; z< historyRepository.count(); z++){
            if(historyRepository.findAll().get(z).getCustomerId().equals(customerId)){
                productArrayList.add(productRepository.findById(historyRepository
                        .findAll()
                        .get(z)
                        .getProductId())
                        .orElse(null));
            }
        }
        return productArrayList;
    }

    public List<Product> customerHistoryInParts(List<HashMap<String, String>> body){
        HashMap<String, String> newBody = body.get(0);
        String customerId = newBody.get("customerId");
        String part = newBody.get("part");  // 1

        List<Product> repository = new ArrayList<>();
        List<Product> list = new ArrayList<>();
        int numberPart = Integer.parseInt(part);
        int mult = numberPart * increase; // 5

        for(int z=0; z<historyRepository.count(); z++){
            if(historyRepository.findAll().get(z).getCustomerId().equals(customerId)){
                String productId = historyRepository.findAll().get(z).getProductId();
                Product product = productRepository.findById(productId).orElse(null);
                if(product != null){
                    repository.add(product);
                }
            }
        }

        int repositoryLength = repository.size();
        int start = mult - increase; // 0
        int end = repositoryLength > mult ? mult : repositoryLength;

        if(repositoryLength > start){
            list = repository.subList(start, end);
        }
        return list;
    }



    public OutLaw deleteAllCustomerHistory(HashMap<String, String> body ){

        String customerId = body.get("customerId");
        for(int y = 0; y< historyRepository.count(); y++){
            if(historyRepository.findAll().get(y).getCustomerId().equals(customerId)){
                historyRepository.delete(historyRepository.findAll().get(y));
            }
        }
        return new OutLaw("All history deleted");
    }


    public OutLaw deleteCustomerHistoryById(HashMap<String, String> body){

        String customerId = body.get("customerId");
        String productId = body.get("productId");
        historyRepository.delete(historyRepository.findAll().stream()
                .filter(history -> history.getCustomerId().equals(customerId) &&
                        history.getProductId().equals(productId))
                .findFirst()
                .orElse(null));

        return new OutLaw("History deleted");
    }

    public OutLaw deleteAll(){
        historyRepository.deleteAll();
        return new OutLaw("All history deleted");
    }

    public String count(){ return String.valueOf(historyRepository.count()); }


    public OutLaw countCustomerHsitory(HashMap<String, String> body){
        String customerId = body.get("customerId");
        String ms = String.valueOf(historyRepository.findAll().stream()
                .filter(recommend -> recommend.getCustomerId().equals(customerId)).count());
        return new OutLaw(ms);
    }



}
