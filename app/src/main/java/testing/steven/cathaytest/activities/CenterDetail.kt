package testing.steven.cathaytest.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.layout_center_detail.*
import kotlinx.android.synthetic.main.layout_center_detail.loading
import kotlinx.android.synthetic.main.layout_center_detail.rv_center_list
import kotlinx.android.synthetic.main.main_activity.toolbar
import testing.steven.cathaytest.R
import testing.steven.cathaytest.activities.bases.BaseActivity
import testing.steven.cathaytest.adapter.PlantsAdapter
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.datamodel.PlantsDataModel
import testing.steven.cathaytest.viewmodels.CenterDetailViewModel
import testing.steven.cathaytest.vmfactory.BundleFactory
import kotlin.math.abs


class CenterDetail : BaseActivity() {

    lateinit var centerDetailViewModel : CenterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_center_detail)

        uiInit()
        var vmFactory = BundleFactory(this, intent.extras!!)
        centerDetailViewModel = ViewModelProvider(this, vmFactory).get(CenterDetailViewModel::class.java)
        centerDetailViewModel.centerDetailLiveData.observe(this, { center ->
            injectDataUI(center)

        })
        centerDetailViewModel.plantsLiveData.observe(this, { plants ->
            injectDataListUI(plants as ArrayList<PlantsDataModel>)
        })
        centerDetailViewModel.uiStateLiveData.observe(this,{
            apiUIState(it)
        })

    }
    private fun apiUIState(it: Int?) {
        if(it==0){
            rv_center_list.visibility = View.GONE
            loading.visibility = View.VISIBLE
        }else if(it == -1){
            rv_center_list.visibility = View.GONE
            loading.visibility = View.VISIBLE
        }else{
            rv_center_list.visibility = View.VISIBLE
            loading.visibility = View.GONE
        }
    }
    private fun uiInit() {
        var drawable = ContextCompat.getDrawable(this, R.drawable.menu)

        toolbar.navigationIcon = drawable;
        setSupportActionBar(toolbar);

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                abs(verticalOffset) == appBarLayout.totalScrollRange -> {
                    // Collapsed
                    toolbar.navigationIcon = ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.back_arrow
                    )
                }
                verticalOffset == 0 -> {
                    // Expanded
                    toolbar.navigationIcon = ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.white_back_with_shadow
                    )
                }
                else -> {
                    toolbar.navigationIcon = ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.white_back_with_shadow
                    )
                }
            }
        })
    }

    private fun injectDataListUI(plants: ArrayList<PlantsDataModel>) {

                recyclerViewLogic(plants)

    }

    private fun recyclerViewLogic(plants: java.util.ArrayList<PlantsDataModel>) {
        var adapter = rv_center_list.adapter as PlantsAdapter?
        if (adapter == null) {
            adapter = PlantsAdapter(plants, this)
            adapter.setHasStableIds(true)
            var layoutManager = LinearLayoutManager(this)
            rv_center_list.adapter = adapter
            rv_center_list.layoutManager = layoutManager
            val dividerItemDecoration = DividerItemDecoration(
                rv_center_list.getContext(),
                layoutManager.orientation
            )
            rv_center_list.addItemDecoration(dividerItemDecoration)
        } else {
            adapter.updateData(plants)
        }

    }

    private fun injectDataUI(center: CenterDataModel) {
        imgBackground.setImageURI(center.pictureUrl)
        longDescription.text = center.longDescription
        toolbar.title = center.name

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener {
            finish()
        }
        if(center.memo.isEmpty()){
            memo.text = getString(R.string.app_no_memo)
        }else{
            memo.text = center.memo
        }
        category.text = center.category
        link.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(center.url)
            try {
                startActivity(i)
            } catch (ac: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    getString(R.string.browser_issue),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}