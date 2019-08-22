package jonathaseloi.com.compilationofthings.dialog

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import jonathaseloi.com.compilationofthings.R
import android.R.attr.data
import android.support.annotation.IntegerRes
import android.util.FloatProperty
import android.util.TypedValue

class CustomDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_dialog)

        createDialog()
    }

    @SuppressLint("InflateParams")
    private fun createDialog() {
        var _view: View = this.layoutInflater.inflate(R.layout.dialog_custom, null)

        var btnAplicar: Button = _view.findViewById(R.id.btnAplicar)

        btnAplicar.setOnClickListener {

            Toast.makeText(this, "Sim", Toast.LENGTH_LONG).show()
        }

        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setView(_view)


        var dialogCard = alertDialog.create()

        val wmlp = dialogCard.window?.attributes
        wmlp?.gravity = Gravity.TOP
        wmlp?.verticalMargin = 0.1F

        dialogCard.show()
    }
}
