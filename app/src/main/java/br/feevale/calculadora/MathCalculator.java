package br.feevale.calculadora;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pc on 21/10/2017.
 */

/**
 * Classe responsável pelos calculos realizados pelo programa
 */
public class MathCalculator {

    /**
     * Executa e retorna valor calculado
     *
     * @param valores Lista de valores
     * @param operadores Lista de operadores
     * @return double
     * @throws Exception Problema durante a execução e conversão dos valores para o cálculo
     */
    public double calculaValores(ArrayList<String> valores, ArrayList<String> operadores) throws Exception {
        double resultado = 0;
        // Converte o primeiro valor da lista
        resultado = Double.parseDouble(valores.get(0).replace(",", "."));
        // Caso o primeiro operador seja de subtração se entende que o primeiro número é negativo
        if(operadores.size() >= valores.size() && operadores.get(0).equals("-")){
            resultado = resultado * -1;
            operadores.remove(0);
        }
        // Percorre os valores montando a operação a ser utilizada
        for(int i = 1; i <= valores.size() - 1; i++) {
            switch (operadores.get(i-1)){
                case "+":
                    resultado = resultado + Double.parseDouble(valores.get(i).replace(",", "."));
                    break;
                case "-":
                    resultado = resultado - Double.parseDouble(valores.get(i).replace(",", "."));
                    break;
                case "*":
                    resultado = resultado * Double.parseDouble(valores.get(i).replace(",", "."));
                    break;
                case "/":
                    if(Double.parseDouble(valores.get(i)) == 0){
                        throw new Exception("Não é possível executar divisão por zeros");
                    }
                    resultado = resultado / Double.parseDouble(valores.get(i).replace(",", "."));
                    break;
            }
        }
        return  resultado;
    }

    /**
     * Método responsável por resolver as expressões mátematicas
     *
     * @param values Lista contendo as expressões adicionadas
     * @return String
     */
    public String resolve(String values) {
        // Lista contendo todos os valores a serem calculados
        ArrayList<String> nValues = new ArrayList<String>(Arrays.asList(values.split("[\\+\\-\\/\\*]")));
        // Lista contendo todas as operaçoes a serem efetuadas
        ArrayList<String> operations = new ArrayList<String>(Arrays.asList(values.split("[0-9]?[0-9]"))) ;
        // Remove espaços e virgulas que possam ter ficado depois da expressão regular
        operations = removeWhiteSpaceAndVirgula(operations);
        // Remove espaços que possam ter ficado depois da expressão regular
        nValues = removeWhiteSpaceAndVirgula(nValues);
        double resultado  = 0;
        try{
            // Executa cálculo das exporessões
            resultado = calculaValores(nValues, operations);
        }catch (Exception ex) {
            System.out.println(ex);
            resultado = 0;
        }
       return String.valueOf(resultado);
    }

    /**
     * Método responsável por remover espaçoes em brancos e virgulas que não estão com algum número
     *
     * @param list Lista contendo dados
     * @return {@link ArrayList}
     */
    private ArrayList<String> removeWhiteSpaceAndVirgula(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<String>();
        for(int i =0; i< list.size(); i++) {
            if(!list.get(i).equals("") && !list.get(i).equals(",")){
                newList.add(list.get(i));
            }
        }
        return newList;
    }
}
