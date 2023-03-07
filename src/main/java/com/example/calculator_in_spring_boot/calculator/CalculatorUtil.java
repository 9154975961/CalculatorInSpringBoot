package com.example.calculator_in_spring_boot.calculator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorUtil {
    public static String calc(String expression) {    //Принимаем значения полученные на сайте

        List<String> listOfSymbols = new ArrayList<>();
        String result = null;

        addSymbolsInList(listOfSymbols, expression);

            checkingForSquareAndSqrt(listOfSymbols);  //проверяем корень\степень
            checkingForParentheses(listOfSymbols);  //проверяем скобки
            multiplicationAndDivision(listOfSymbols);   //проверяем умножение деление
            additionAndSubtraction(listOfSymbols);  //проверяем сложение вычитание

        if (listOfSymbols.size() < 3 && !(listOfSymbols.contains("делить на ноль нельзя"))) {
            //
            if(listOfSymbols.size()==2){
                result = listOfSymbols.get(0) + listOfSymbols.get(1);
            }
            if (listOfSymbols.size()==2){
                result = listOfSymbols.get(0);
            } else result = String.join("", listOfSymbols);
            double number = Double.parseDouble(result);
            result = String.valueOf(Math.round(number * 100.0) / 100.0);
        } else result = listOfSymbols.get(0);

        return result;
    }

    private static void checkingForSquareAndSqrt(List<String> listOfSymbols) {
        while (listOfSymbols.indexOf("√") >= 0 || listOfSymbols.indexOf("^") >= 0){ //проверяем корни и степени
            if(listOfSymbols.lastIndexOf("√") > listOfSymbols.lastIndexOf("^")) {
                if (listOfSymbols.lastIndexOf("√") == 0 ||
                        listOfSymbols.get(listOfSymbols.lastIndexOf("√") - 1).equals("+") ||   //если перед знаком нет числа (степени)
                        listOfSymbols.get(listOfSymbols.lastIndexOf("√") - 1).equals("-") ||
                        listOfSymbols.get(listOfSymbols.lastIndexOf("√") - 1).equals("*") ||
                        listOfSymbols.get(listOfSymbols.lastIndexOf("√") - 1).equals("/")) {
                    if (!listOfSymbols.get(listOfSymbols.lastIndexOf("√") + 1).equals("(") && (listOfSymbols.lastIndexOf("√") + 2 >= listOfSymbols.size() ||
                            !listOfSymbols.get(listOfSymbols.lastIndexOf("√") + 2).equals("^"))) {
                        double result = Math.sqrt(Double.parseDouble(listOfSymbols.get(listOfSymbols.lastIndexOf("√") + 1)));
                        int number = listOfSymbols.lastIndexOf("√");
                        listOfSymbols.set(number, String.valueOf(result));
                        listOfSymbols.remove(number + 1);
                        if (listOfSymbols.size() == 2 && listOfSymbols.get(0).equals("-")) {
                            listOfSymbols.set(0, String.valueOf(listOfSymbols.get(1)));
                        }
                    }
                } else if (!(listOfSymbols.get(listOfSymbols.lastIndexOf("√") + 1).equals("("))) { //если перед знаком и за знаком стоит число
                    double x = Double.valueOf(listOfSymbols.get(listOfSymbols.lastIndexOf("√") + 1));
                    double root = 1.0 / Double.valueOf(listOfSymbols.get(listOfSymbols.lastIndexOf("√") - 1));
                    double result = Math.pow(x, root);
                    listOfSymbols.remove(listOfSymbols.lastIndexOf("√") + 1);
                    listOfSymbols.set(listOfSymbols.lastIndexOf("√") - 1, String.valueOf(result));
                    listOfSymbols.remove(listOfSymbols.lastIndexOf("√"));

                } else if ((listOfSymbols.get(listOfSymbols.lastIndexOf("√") + 1).equals("("))) {    //иначе под знаком стоит открывающая скобка
                    checkingForParentheses(listOfSymbols);
                }
            } else {
                if (listOfSymbols.lastIndexOf("^") >= 0) {  // проверяем что перед символом стоит число, а перед ним символ
                    if ((listOfSymbols.lastIndexOf("^") - 2) >= 0 && (listOfSymbols.get(listOfSymbols.lastIndexOf("^") - 2).equals("+") ||
                            listOfSymbols.get(listOfSymbols.lastIndexOf("^") - 2).equals("-") ||
                            listOfSymbols.get(listOfSymbols.lastIndexOf("^") - 2).equals("*") ||
                            listOfSymbols.get(listOfSymbols.lastIndexOf("^") - 2).equals("/"))) {
                        goInSquare(listOfSymbols);
                    }
                    if (listOfSymbols.lastIndexOf("^") == 1 && !(listOfSymbols.get(listOfSymbols.lastIndexOf("^")+1).equals("("))) {
                        goInSquare(listOfSymbols); // если возвенедение стоит первым и за ним нет скобки, то просто возвести
                    }
                    if(listOfSymbols.lastIndexOf("^") > 0 && (listOfSymbols.get(listOfSymbols.lastIndexOf("^")-1).equals(")") ||
                            listOfSymbols.get(listOfSymbols.lastIndexOf("^")+1).equals("("))){
                        checkingForParentheses(listOfSymbols);  //если стоят скобки перед или после возведения, то сначала решить скобки
                    }
                }
            }
        }
    }

    private static void goInSquare(List<String> listOfSymbols) {
        double x = Double.valueOf(listOfSymbols.get(listOfSymbols.lastIndexOf("^")-1));
        double root = Double.valueOf(listOfSymbols.get(listOfSymbols.lastIndexOf("^")+1));
        double result = Math.pow(x, root);
        listOfSymbols.remove(listOfSymbols.lastIndexOf("^")+1);
        listOfSymbols.set(listOfSymbols.lastIndexOf("^")-1, String.valueOf(result));
        listOfSymbols.remove(listOfSymbols.lastIndexOf("^"));
    }
    private static List<String> checkingForParentheses(List<String> listOfSymbols) {
            while (listOfSymbols.indexOf("(") >= 0 && listOfSymbols.indexOf(")") > 0) {  //проверяем есть ли открывающие и закрывающие скобки
                List<String> evaluate = new ArrayList<>();
                for (int i = 0; i < listOfSymbols.size(); i++) {    //создаем новый лист и записываем в него пример
                    String a = listOfSymbols.get(i);
                    evaluate.add(a);
                }
                int deliter1 = 0, deliter2 = 0, deliterFull = 0, count = 0;

                while (evaluate.indexOf("(") >= 0) {   // пока есть открывающие скобки

                    deliter1 = evaluate.indexOf("(");
                    for (int i = 0; i < deliter1 + 1; i++) {
                        evaluate.remove(0);
                    }

                    deliterFull = deliterFull + deliter1;
                    count++;
                }
                if (count != 0) count = count - 1;
                deliterFull = deliterFull + count;

                deliter2 = evaluate.indexOf(")");   // ищем закрывающую скобки
                int endListSize = evaluate.size() - deliter2;

                for (int i = 0; i < endListSize; i++) { //удаляем все что за скобками в месте со скобкой
                    evaluate.remove(deliter2);
                }

                multiplicationAndDivision(evaluate);
                additionAndSubtraction(evaluate);

                while (!(listOfSymbols.get(deliterFull).equals(")"))) {  //подчищаем все в основном примере и записываем ответ
                    listOfSymbols.remove(deliterFull);
                }
                listOfSymbols.set(deliterFull, evaluate.get(0));    //тут записываем
            }
            return listOfSymbols;
    }

    private static void addSymbolsInList(List<String> listOfSymbols, String expression) {   //переводим стринг в лист
        String[] tokens = expression.replaceAll("\\s+", "").split("(?<=[-+*/()^√])|(?=[-+*/()^√])");

        for (int i = 0; i < tokens.length; i++) {   // Добавляем элементы в список
            String part = tokens[i];
            if(i==0 && part.equals("-") && !tokens[i+1].equals("√")) {  //проверяем стоит ли минус в начале выражения
                part = tokens[0] + tokens[1];
                listOfSymbols.add(part);
                i++;
            } else if(part.equals("(")){    //проверяем есть ли за скобка
                if (tokens[i+1].equals("-") && tokens[i+3].equals(")")){    //проверяем есть ли за скобкой минус и закрытие скобки
                    part = tokens[i+1] + tokens[i+2];
                    listOfSymbols.add(part);
                    i = i+3;
                } else if(tokens[i+1].equals("-") && (tokens[i+3].equals("+") ||  tokens[i+3].equals("/") ||
                        tokens[i+3].equals("*") || tokens[i+3].equals("-"))){   //проверяем что в скобках есть выражение
                    part = tokens[i];                                                                           //а первое число с отрицательным знаком
                    listOfSymbols.add(part);
                    part = tokens[i+1] + tokens[i+2];
                    listOfSymbols.add(part);
                    i = i+2;
                } else if(tokens[i+2].equals("+") ||  tokens[i+2].equals("/") || tokens[i+2].equals("*") || tokens[i+2].equals("-")){
                    part = tokens[i];
                    listOfSymbols.add(part);
                }


            }else if (tokens[i].equals("√") && i+1 < tokens.length && tokens[i+1].equals("-") && i+2 <= tokens.length){
                part = tokens[i];
                listOfSymbols.add(part);
                part = tokens[i+1] + tokens[i+2];
                listOfSymbols.add(part);
            }else listOfSymbols.add(part);

        }
    }

    private static void multiplicationAndDivision(List<String> listOfSymbols) {  // умножение и деление
            while (listOfSymbols.indexOf("*") > 0 || listOfSymbols.indexOf("/") > 0) {    //проверяем есть ли умножение или деление

                if (listOfSymbols.indexOf("*") > listOfSymbols.indexOf("/") && listOfSymbols.indexOf("/") > 0) {    //проверяем что первым стоит деление
                    indexLookup(listOfSymbols.indexOf("/"), listOfSymbols);
                } else if (listOfSymbols.indexOf("*") < listOfSymbols.indexOf("/") && listOfSymbols.indexOf("/") > 0) {
                    indexLookup(listOfSymbols.indexOf("/"), listOfSymbols);
                } else indexLookup(listOfSymbols.indexOf("*"), listOfSymbols);
            }
    }

    private static void additionAndSubtraction(List<String> listOfSymbols){  //сложение вычитание
            while (listOfSymbols.contains("+") || listOfSymbols.contains("-")) {    //проверяем есть ли сложение или вычитание
                if (listOfSymbols.indexOf("+") > listOfSymbols.indexOf("-") && listOfSymbols.indexOf("-") > 0) {
                    indexLookup(listOfSymbols.indexOf("-"), listOfSymbols);
                } else if (listOfSymbols.indexOf("+") < listOfSymbols.indexOf("-") && listOfSymbols.indexOf("-") > 0) {
                    indexLookup(listOfSymbols.indexOf("-"), listOfSymbols);
                } else indexLookup(listOfSymbols.indexOf("+"), listOfSymbols);
            }
    }
    private static void indexLookup(int index, List<String> listOfSymbols) {
        double numberOne;
        double numberTwo;

        numberOne = Double.valueOf(listOfSymbols.get(index - 1));   //записываем число которое стоит перед знаком операции
        numberTwo = Double.valueOf(listOfSymbols.get(index + 1));   //записываем число которое стоит после знака операции

        listOfSymbols.set(index, mach(listOfSymbols.get(index), numberOne, numberTwo));  //записываем результат

        listOfSymbols.remove(index - 1);    //удаляем использованные элементы
        listOfSymbols.remove(index);    //удаляем использованные элементы
    }

    private static String mach (String operator, double number1, double number2){    //Математические действия
        double result;

        switch (operator) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if(number2!=0)
                result = number1 / number2;
                else return "делить на ноль нельзя";
                break;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
        return String.valueOf(result);
    }
}
