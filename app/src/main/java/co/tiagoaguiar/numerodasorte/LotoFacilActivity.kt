package co.tiagoaguiar.numerodasorte

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class LotoFacilActivity : AppCompatActivity() {

    // variável de campo, pode ser usada em toda classe man.
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loto_facil)
        //buscar os objetos e ter a referencia para manipula-los
        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

        //Banco de dados de preferencias.
        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
        val result = prefs.getString("result", null)

        //modo 1
        /* if (result != null) {
             txtResult.text = "Ultima aposta: $result"
         }*/

        //modo 2 - let
        result?.let {
            txtResult.text = "Ultima aposta: $it"
        }

        //modo 3 - passe um valor diferente de null
        /*txtResult.text = "Ultima aposta: $result"*/


        btnGenerate.setOnClickListener() {
            val text = editText.text.toString()
            numberGenerated(text, txtResult)
        }

    }

    private fun numberGenerated(text: String, txtResult: TextView) {
        //Falha 1 = validar se o campo está vazio
        if (text.isEmpty()) {
            Toast.makeText(this, "Digite um número entre 15 e 20", Toast.LENGTH_LONG).show()
            return
        }
        //Falha 2 =  validar se digitaram caracteres dentro entre 6 e 15
        val qtd = text.toInt()
        if (qtd < 15 || qtd > 20) {
            Toast.makeText(this, "Digite um número entre 15 e 20", Toast.LENGTH_LONG).show()
            return
        }
        // Sucesso!!

        val numbers = mutableSetOf<Int>()
        val random = Random()

        while (true) {
            val number = random.nextInt(25)
            numbers.add(number + 1)
            if (numbers.size == qtd) {
                break
            }
        }

        txtResult.text = numbers.sorted().joinToString("-")

        // modo 1 - bucando a referencia do objeto, e chamando objeto e função.
        /*val editor = prefs.edit()
        editor.putString("result", txtResult.text.toString())
        editor.apply()*/

        //modo2 - pega-se o objeto e aplica (apply) as preferencias dentro dos {}
        prefs.edit().apply() {
            putString("result", txtResult.text.toString())
            apply()

        }


        //salvar:
        //.apply() - Salvar de forma assincrona, não vai bloquear a interface gráfica e não informar se obteve ou não sucesso
        //.commit() - Salvar de forma sincrona, vai bloquear a interface gráfica e informar se obteve ou não sucesso

    }
}