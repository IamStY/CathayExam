package testing.steven.cathaytest.components

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.layout_center_detail.*
import kotlinx.android.synthetic.main.main_fragment.rv_center_list
import testing.steven.cathaytest.R
import testing.steven.cathaytest.adapter.CenterAdapter
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.viewmodels.MainViewModel
import testing.steven.cathaytest.vmfactory.ContextFactory

/***********
 * 主頁面園區列表Fragment
 */
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.run {
            initVm(this)
        }
    }

    private fun initVm(context: Context) {
        var vmFactory = ContextFactory(context)
        viewModel = ViewModelProvider(this@MainFragment, vmFactory).get(MainViewModel::class.java)
        viewModel.centerLiveData.observe(viewLifecycleOwner, { centers ->
            injectUI(centers as ArrayList<CenterDataModel>)

        })
        viewModel.uiStateLiveData.observe(viewLifecycleOwner, Observer {
            apiUIState(it)
        })
    }

    private fun apiUIState(it: Int?) {
        if (it == 0) {
            rv_center_list.visibility = View.GONE
            loading.visibility = View.VISIBLE
        } else if (it == -1) {
            rv_center_list.visibility = View.GONE
            loading.visibility = View.VISIBLE
            loading.findViewById<TextView>(R.id.status).text = getString(R.string.no_network)
            var lottie = loading.findViewById<LottieAnimationView>(R.id.lottie)
            lottie.setAnimation(R.raw.empty)
            lottie.playAnimation()
        } else {
            rv_center_list.visibility = View.VISIBLE
            loading.visibility = View.GONE
        }
    }

    private fun injectUI(centers: ArrayList<CenterDataModel>) {
        recyclerViewLogic(centers)
    }

    private fun recyclerViewLogic(centers: java.util.ArrayList<CenterDataModel>) {
        var adapter = rv_center_list.adapter as CenterAdapter?
        if (adapter == null) {
            adapter = CenterAdapter(centers, activity)
            adapter.setHasStableIds(true)
            var layoutManager = LinearLayoutManager(activity)
            rv_center_list.adapter = adapter
            rv_center_list.layoutManager = layoutManager
            val dividerItemDecoration = DividerItemDecoration(
                rv_center_list.getContext(),
                layoutManager.orientation
            )
            rv_center_list.addItemDecoration(dividerItemDecoration)
        } else {
            adapter.updateData(centers)
        }
    }

}