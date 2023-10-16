package co.tiagoaguiar.numerodasorte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var btnMega: Button
    private lateinit var btnQuina: Button
    private lateinit var btnLotoFacil: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMega = findViewById(R.id.btn_mega)
        btnMega.setOnClickListener {
            val mega = Intent(this, MegaActivity::class.java)
            startActivity(mega)
        }

        btnQuina = findViewById(R.id.btn_quina)
        btnQuina.setOnClickListener {
            val quina = Intent(this, QuinaActivity::class.java)
            startActivity(quina)
        }

        btnLotoFacil = findViewById(R.id.btn_loto_facil)
        btnLotoFacil.setOnClickListener {
            val lotoFacil = Intent(this, LotoFacilActivity::class.java)
            startActivity(lotoFacil)
        }



    }


}




