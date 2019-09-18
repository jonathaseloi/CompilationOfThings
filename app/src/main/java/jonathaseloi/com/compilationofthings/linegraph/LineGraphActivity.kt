package jonathaseloi.com.compilationofthings.linegraph

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.farshid_roohi.linegraph.ChartEntity
import jonathaseloi.com.compilationofthings.R
import kotlinx.android.synthetic.main.activity_line_graph.*

class LineGraphActivity : AppCompatActivity() {

    private val graph1 = floatArrayOf(113000f, 183000f, 188000f, 695000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f)
    private val graph2 = floatArrayOf(0f, 245000f, 1011000f, 1000f, 0f, 0f, 47000f, 20000f, 12000f, 124400f, 160000f)
    private val legendArr = arrayOf("05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28", "05/29", "05/30", "05/31")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_graph)

        val firstChartEntity = ChartEntity(Color.WHITE, graph1)
        val secondChartEntity = ChartEntity(Color.YELLOW, graph2)

        val list = ArrayList<ChartEntity>()
        list.add(firstChartEntity)
        list.add(secondChartEntity)
        lineChart.legendArray = legendArr
        lineChart.setList(list)
    }
}