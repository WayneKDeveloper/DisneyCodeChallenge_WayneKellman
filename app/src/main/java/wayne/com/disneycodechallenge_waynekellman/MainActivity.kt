package wayne.com.disneycodechallenge_waynekellman

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class MainActivity : AppCompatActivity(){

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appComponent = DaggerApplicationComponent.builder().nameModule(NameModule()).build()
        appComponent.inject(this)

    }

    override fun onResume() {
        super.onResume()
        val haveRes= findViewById<RecyclerView>(R.id.have_rec)
        haveRes.adapter = viewModel.nameAdapter1
        haveRes.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        val needRes= findViewById<RecyclerView>(R.id.need_rec)
        needRes.adapter = viewModel.nameAdapter2
        needRes.layoutManager = object: LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        findViewById<ImageView>(R.id.popup_cancel).setOnClickListener {
            findViewById<ConstraintLayout>(R.id.need_res_popup).visibility = View.GONE
        }

        viewModel.popupLiveData.observe(this) {
            it?.getContentIfNotHandled()?.let {
                if (it) {
                    findViewById<ConstraintLayout>(R.id.need_res_popup).visibility = View.VISIBLE
                } else
                {
                    findViewById<ConstraintLayout>(R.id.need_res_popup).visibility = View.GONE
                }
            }
        }
        viewModel.buttonLiveData.observe(this) {
            it?.getContentIfNotHandled()?.let {
                val button = findViewById<Button>(R.id.continue_button)
                button.isEnabled = it
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val scroll = findViewById<NestedScrollView>(R.id.nest_scroll)
            val text = findViewById<TextView>(R.id.has_res)
            val text2 = findViewById<TextView>(R.id.need_res)
            val title = findViewById<TextView>(R.id.title)
            scroll.setOnScrollChangeListener { view, i, i2, i3, i4 ->
                val scrollBounds = Rect()
                scroll.getHitRect(scrollBounds)
                if (text.getLocalVisibleRect(scrollBounds)) {
                    title.text = getString(R.string.select_guest)
                } else if (!text.getLocalVisibleRect(scrollBounds) && haveRes.getLocalVisibleRect(scrollBounds)) {
                    title.text = getString(R.string.have_reservations)
                } else if (!text2.getLocalVisibleRect(scrollBounds)){
                    title.text = getString(R.string.need_reservations)
                }
            }
        }
    }
}
