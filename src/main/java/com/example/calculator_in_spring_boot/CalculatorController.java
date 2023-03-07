package com.example.calculator_in_spring_boot;
import com.example.calculator_in_spring_boot.calculator.CalculatorUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String showForm(Model model) {
        model.addAttribute("result", "");
        return "calculator";
    }

    @PostMapping("/calculator")
    public String calculate(@RequestParam(name = "number1") String  number1,
                            Model model) {
        String  result = CalculatorUtil.calc(number1);

        model.addAttribute("result", result);
        return "calculator";
    }
}
