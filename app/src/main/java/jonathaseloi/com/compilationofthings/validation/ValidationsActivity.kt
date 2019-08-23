package jonathaseloi.com.compilationofthings.validation

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jonathaseloi.com.compilationofthings.R
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_date_picker.*
import kotlinx.android.synthetic.main.activity_validations.*
import java.text.SimpleDateFormat
import java.util.*


class ValidationsActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validations)

        initView()
        validations()
        cadastrar()
    }

    private fun cadastrar() {
        btnCadastrar.setOnClickListener {
            if (tietNomeCompleto.text.toString().isBlank()) {
                tilNomeCompleto.error = "Nome é obrigatório."
            }
            if (tietProfissao.text.toString().isBlank()) {
                tilProfissao.error = "Profissão é obrigatória."
            }
            if (tvDataNascimento.text.toString().equals("Data Nascimento: --/--/----")) {
                tilDate.error = "Data de nascimento é obrigatória."
            }
        }
    }

    private fun validations() {
        tietNomeCompleto.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                tilNomeCompleto.error =
                    if (p0!!.length < 4) {
                        "Nome precisa ter mais que 4 caracteres"
                    } else null
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        tietProfissao.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                tilProfissao.error = null
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun initView() {
        tvDataNascimento!!.text = "Data Nascimento: --/--/----"

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        tilDate!!.setOnClickListener {
            DatePickerDialog(this@ValidationsActivity,
                dateSetListener,

                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        tvDataNascimento!!.text = "Data Nascimento: " + sdf.format(cal.time)
        tilDate.error = null
    }
}
