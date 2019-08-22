package jonathaseloi.com.compilationofthings.validation

import java.util.InputMismatchException
import java.util.regex.Pattern

object validationUtils {
    fun validarEmail(email: String): Boolean {
        val pattern = Pattern.compile("^[_a-z0-9]+(\\.[_a-z0-9]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$")
        return pattern.matcher(email).matches()
    }

    fun validarSenha(senha: String): Boolean {
        val p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,15}$")
        return p.matcher(senha).matches()
    }

    fun isValidCPF(CPF: String?): Boolean {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF == null || CPF == "00000000000" || CPF == "11111111111"
            || CPF == "22222222222" || CPF == "33333333333"
            || CPF == "44444444444" || CPF == "55555555555"
            || CPF == "66666666666" || CPF == "77777777777"
            || CPF == "88888888888" || CPF == "99999999999"
            || CPF.length != 11
        )
            return false

        val dig10: Char
        val dig11: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int

        // "try" - protege o codigo para eventuais erros de conversao de ordinalTipoGaleria
        // (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0
            peso = 10
            i = 0
            while (i < 9) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = CPF[i].toInt() - 48
                sm = sm + num * peso
                peso = peso - 1
                i++
            }

            r = 11 - sm % 11
            if (r == 10 || r == 11)
                dig10 = '0'
            else
                dig10 = (r + 48).toChar() // converte no respectivo caractere
            // numerico

            // Calculo do 2o. Digito Verificador
            sm = 0
            peso = 11
            i = 0
            while (i < 10) {
                num = CPF[i].toInt() - 48
                sm = sm + num * peso
                peso = peso - 1
                i++
            }

            r = 11 - sm % 11
            if (r == 10 || r == 11)
                dig11 = '0'
            else
                dig11 = (r + 48).toChar()

            // Verifica se os digitos calculados conferem com os digitos
            // informados.
            return dig10 == CPF[9] && dig11 == CPF[10]
        } catch (erro: InputMismatchException) {
            return false
        }

    }
}
