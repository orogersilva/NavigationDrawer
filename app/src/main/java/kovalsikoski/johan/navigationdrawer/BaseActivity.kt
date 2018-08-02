package kovalsikoski.johan.navigationdrawer

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View.inflate
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_base.*


open class BaseActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun setContentView(layoutResID: Int) {

        mDrawerLayout = inflate(this, R.layout.activity_base, null) as DrawerLayout
        val activityContainer = mDrawerLayout.findViewById<View>(R.id.activity_content) as FrameLayout

        layoutInflater.inflate(layoutResID, activityContainer, true)
        setContentView(mDrawerLayout)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val actionbar = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }

        title = "Activity Title"

        setUpNavigationView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }

            R.id.nav_newgame -> {
                Toast.makeText(this, "New Game", Toast.LENGTH_SHORT).show()
                mDrawerLayout.closeDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpNavigationView() {

        navigationView.setNavigationItemSelectedListener { menuItem ->

            drawerLayout.closeDrawers()
            navigateToDrawerItem(menuItem)

             true
        }
    }

    private fun navigateToDrawerItem(menuItem: MenuItem) {

        var targetIntent: Intent? = null

        when (menuItem.itemId) {

            R.id.nav_newgame -> {

                targetIntent = Intent(this, NewGameActivity::class.java)
            }

            R.id.nav_help -> {

                // TODO: To implement to "help screen".
            }
        }

        drawerLayout.closeDrawers()

        targetIntent?.let {
            startActivity(targetIntent)
        }
    }
}