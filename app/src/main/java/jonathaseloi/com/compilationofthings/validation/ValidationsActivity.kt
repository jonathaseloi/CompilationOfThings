package jonathaseloi.com.compilationofthings.validation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jonathaseloi.com.compilationofthings.R
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_validations.*


class ValidationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validations)

        textValidations()
    }

    private fun textValidations() {
        tietUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                tilUsername.error =
                    if (p0!!.length < 4) {
                        "Nome precisa ter mais que 4 caracteres"
                    } else null
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
}
