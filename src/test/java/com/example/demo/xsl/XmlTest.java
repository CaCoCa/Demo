package com.example.demo.xsl;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("banks")
public class XmlTest {


    @XStreamAlias("bank")
    private String bank;

}
