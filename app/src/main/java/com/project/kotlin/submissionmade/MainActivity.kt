package com.project.kotlin.submissionmade

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.project.kotlin.submissionmade.ui.movie.MovieFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "List Movie"

        val mFragmentManager = supportFragmentManager
        val movieFormatError = MovieFragment()
        val fragment = mFragmentManager.findFragmentByTag(MovieFragment::class.java.simpleName)
        if (fragment !is MovieFragment) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, movieFormatError, MovieFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_favorite -> {
                val uri = Uri.parse("submissionmade://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                return true
            }
            else -> true
        }
    }
}