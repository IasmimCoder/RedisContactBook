package com.ifpb.RedisContactBook.utilities;

public class Validations {
    public static boolean validarTelefone(String telefone){
        //retira todos os caracteres não-numéricos (incluindo espaço,tab, etc)
        telefone = telefone.replaceAll("\\D","");

        if (telefone.startsWith("0800")) {

            //verifica se tem a qtde de numeros correta
            if (telefone.length() == 11){
                return true;
            }
            return false;
        }

        //verifica se tem a qtde de numeros correta
        if (!(telefone.length() >= 8 && telefone.length() <= 10)) return false;

        //verifica se o numero foi digitado com todos os dígitos iguais
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(telefone.charAt(0)+"{"+telefone.length()+"}");
        java.util.regex.Matcher m = p.matcher(telefone);
        if(m.find()) return false;

        return true;
    }

    public static boolean validarDDD(String ddd){

        String[] DDDs = {
                "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "21", "22", "24", "27", "28", "31", "32", "33", "34",
                "35", "37", "38", "41", "42", "43", "44", "45", "46",
                "47", "48", "49", "51", "53", "54", "55", "61", "62",
                "64", "63", "65", "66", "67", "68", "69", "71", "73",
                "74", "75", "77", "79", "81", "82", "83", "84", "85",
                "86", "87", "88", "89", "91", "92", "93", "94", "95",
                "96", "97", "98", "99"};

        for(String d: DDDs){
            if(ddd.equals(d)){
                return true;
            }
        }
        return false;
    }
}