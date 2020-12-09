package testing.steven.cathaytest.activities

import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.main_activity.*
import testing.steven.cathaytest.R
import testing.steven.cathaytest.activities.bases.BaseActivity
import testing.steven.cathaytest.components.MainFragment

/***********
 *主入口
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        var drawable = ContextCompat.getDrawable(this, R.drawable.menu)
        toolbar.navigationIcon = drawable
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}