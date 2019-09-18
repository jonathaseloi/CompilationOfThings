package jonathaseloi.com.compilationofthings.cardslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jonathaseloi.com.compilationofthings.R
import kotlinx.android.synthetic.main.activity_card_slider.*

class CardSliderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_slider)

        val movies = ArrayList<Movie>().apply{
            add(Movie(R.drawable.harry_potter,"Harry Potter",
                "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world."
            ))

            add(
                Movie(R.drawable.lord_of_rings,"The Lord of the Rings",
                    "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron."
                ))

            add(
                Movie(R.drawable.the_matrix,"The Matrix",
                    "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers."
                ))

            add(
                Movie(R.drawable.avengers,"Avengers Assemble",
                    "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity."
                ))
        }

        viewPager.adapter = MovieAdapter(movies)
    }
}
