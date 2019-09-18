package jonathaseloi.com.compilationofthings.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.View.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import jonathaseloi.com.compilationofthings.R
import kotlinx.android.synthetic.main.dialog_custom.view.*

class CustomDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_dialog)

        createDialog()
    }

    @SuppressLint("InflateParams")
    private fun createDialog() {
        var view: View = this.layoutInflater.inflate(R.layout.dialog_custom, null)
        view.ivBob.setOnClickListener {
            view.tvBob.visibility = if (view.tvBob.visibility == INVISIBLE) VISIBLE else INVISIBLE
        }

        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setView(view)

        var dialogCard = alertDialog.create()

        //Position on Screen
        val wmlp = dialogCard.window?.attributes
        wmlp?.gravity = Gravity.TOP
        wmlp?.verticalMargin = 0.3F

        dialogCard.show()
    }
}
