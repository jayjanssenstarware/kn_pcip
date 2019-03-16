package kn.swift.pcip.service;

import kn.swift.mf.UC_PACKAGE_CONFIRMATION2017_01.UC_PACKAGE_CONFIRMATION2017_01MapToKNCL01Service_XSD1;
import com.altova.io.StringInput;
import com.altova.io.StringOutput;
import org.springframework.stereotype.Service;

@Service
public class Transformer {


    public static String transform(String s){

        UC_PACKAGE_CONFIRMATION2017_01MapToKNCL01Service_XSD1 xform = new UC_PACKAGE_CONFIRMATION2017_01MapToKNCL01Service_XSD1();

        StringInput input = new StringInput(s);
        StringOutput output = new StringOutput();

        try {
            xform.run(input, output);
        } catch (Exception e){
            return "Error in transformation: " + e.getMessage();
        }
        return output.getString().toString();

    }

}