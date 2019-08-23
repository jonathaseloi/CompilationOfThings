package jonathaseloi.com.compilationofthings.datepicker

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import jonathaseloi.com.compilationofthings.R
import kotlinx.android.synthetic.main.activity_date_picker.*
import java.text.SimpleDateFormat
import java.util.*

class DatePickerActivity : AppCompatActivity() {

    var textview_date: TextView? = null
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)

        initView()
    }

    private fun initView() {
        textview_date = this.tvDate

        textview_date!!.text = "--/--/----"

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        textview_date!!.setOnClickListener {
            DatePickerDialog(this@DatePickerActivity,
                dateSetListener,

                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textview_date!!.text = sdf.format(cal.time)
    }

}
