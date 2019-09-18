package jonathaseloi.com.compilationofthings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jonathaseloi.com.compilationofthings.cardslider.CardSliderActivity
import jonathaseloi.com.compilationofthings.datepicker.DatePickerActivity
import jonathaseloi.com.compilationofthings.dialog.CustomDialogActivity
import jonathaseloi.com.compilationofthings.recyclerview.RecyclerViewExemploActivity
import jonathaseloi.com.compilationofthings.searchview.SearchViewActivity
import jonathaseloi.com.compilationofthings.searchviewwithfilter.SearchViewWithFilterActivity
import jonathaseloi.com.compilationofthings.validation.ValidationsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRecyclerView.setOnClickListener {
            val intent = Intent(applicationContext, RecyclerViewExemploActivity::class.java)
            startActivity(intent)
        }

        btnSearchView.setOnClickListener {
            val intent = Intent(applicationContext, SearchViewActivity::class.java)
            startActivity(intent)
        }

        btnSearchViewWithFilter.setOnClickListener {
            val intent = Intent(applicationContext, SearchViewWithFilterActivity::class.java)
            startActivity(intent)
        }

        btnDatePicker.setOnClickListener {
            val intent = Intent(applicationContext, DatePickerActivity::class.java)
            startActivity(intent)
        }

        btnValidation.setOnClickListener {
            val intent = Intent(applicationContext, ValidationsActivity::class.java)
            startActivity(intent)
        }

        btnCustomDialog.setOnClickListener {
            val intent = Intent(applicationContext, CustomDialogActivity::class.java)
            startActivity(intent)
        }

        btnCardSlider.setOnClickListener {
            val intent = Intent(applicationContext, CardSliderActivity::class.java)
            startActivity(intent)
        }
    }
}
