package org.example;

import org.example.exception.UnsuportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

@RequestMapping(value="/sum/{numberOne}/{numberTwo}" ,method = RequestMethod.GET)
 public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
        throw new UnsuportedMathOperationException("Please set a numeric value");
    }
    Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
        return sum;
    }

    private Double convertToDouble(String num) {
        if (num == null) return 0d;
        String number = num.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0d;
    }

    private boolean isNumeric(String num) {
        if (num == null) return false;
        String number = num.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
