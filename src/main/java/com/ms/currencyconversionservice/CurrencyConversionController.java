package com.ms.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    public static final String URL ="http://localhost:8000/currency-exchange/from/{from}/to/{to}";

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversion(@PathVariable String from,
                                                    @PathVariable String to,
                                                    @PathVariable int quantity) {

        Map<String, String> varMap = new HashMap<>();
        varMap.put("from", from);
        varMap.put("to", to);
        ResponseEntity<CurrencyConversion> forEntity = new RestTemplate().getForEntity(URL, CurrencyConversion.class, varMap);
        CurrencyConversion cc = forEntity.getBody();
        CurrencyConversion newCc = new CurrencyConversion(cc.getId(), from, to, cc.getConversionRate(), quantity);
        newCc.setEnvironment(cc.getEnvironment());
        return newCc;
    }


    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversionFeign(@PathVariable String from,
                                                         @PathVariable String to,
                                                         @PathVariable int quantity) {

        CurrencyConversion cc = currencyExchangeProxy.retrieveExchangeValue(from,to);

        CurrencyConversion newCc = new CurrencyConversion(cc.getId(), from, to, cc.getConversionRate(), quantity);

        newCc.setEnvironment(cc.getEnvironment()+"  Feign");

        return newCc;
    }


   /* @GetMapping("/currency-conversion-ex/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversionEx(@PathVariable String from, @PathVariable String to, @PathVariable int quantity) {

        Map<String, String> varMap = new HashMap<>();
        varMap.put("from", from);
        varMap.put("to", to);
        ResponseEntity<CurrencyExchange> forEntity = new RestTemplate().getForEntity(URL, CurrencyExchange.class, varMap);
        CurrencyExchange cx = forEntity.getBody();
        CurrencyConversion cc = new CurrencyConversion(cx.getId(), from, to, cx.getConversionRate(), quantity);
        cc.setEnvironment(cx.getEnvironment());
        return cc;
    }*/
}
