package jonathaseloi.com.compilationofthings.httpurlconnection

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.AsyncTask
import android.util.Log
import jonathaseloi.com.compilationofthings.R
import kotlinx.android.synthetic.main.activity_http_url_connection.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HttpUrlConnectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_url_connection)

        FetchWeatherData().execute()
    }

    @SuppressLint("StaticFieldLeak")
    private inner class FetchWeatherData : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void): String? {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            var urlConnection: HttpURLConnection? = null
            var reader: BufferedReader? = null

            // Will contain the raw JSON response as a string.
            val forecastJsonStr: String?

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                val url =
                    URL("https://discuss.kotlinlang.org/t/assignment-not-allow-in-while-expression/339")

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.connect()

                // Read the input stream into a String
                val inputStream = urlConnection.inputStream
                val buffer = StringBuffer()
                if (inputStream == null) {
                    // Nothing to do.
                    return null
                }
                reader = BufferedReader(InputStreamReader(inputStream))

                while (true) {
                    val line = reader.readLine() ?: break

                    buffer.append(line + "\n")
                }

                if (buffer.isEmpty()) {
                    // Stream was empty.  No point in parsing.
                    return null
                }
                forecastJsonStr = buffer.toString()
                return forecastJsonStr
            } catch (e: IOException) {
                Log.e("PlaceholderFragment", "Error ", e)
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null
            } finally {
                urlConnection?.disconnect()
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (e: IOException) {
                        Log.e("PlaceholderFragment", "Error closing stream", e)
                    }

                }
            }
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            tv_weather_json.text = s
            Log.i("json", s)
        }
    }
}
