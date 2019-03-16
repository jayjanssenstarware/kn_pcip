package kn.swift.pcip.flow;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class Printer {

    public void print(String s){
        System.out.println(s);
    }
}