package testing.steven.cathaytest.adapter

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import testing.steven.cathaytest.activities.CenterDetail
import testing.steven.cathaytest.R
import testing.steven.cathaytest.adapter.viewholders.CommonRecyclerViewHolder
import testing.steven.cathaytest.api.Statics
import testing.steven.cathaytest.datamodel.CenterDataModel
import kotlin.collections.ArrayList

/***********
 * 園區列表
 */
class CenterAdapter(var centers: ArrayList<CenterDataModel>, val activity: Activity?) :
    RecyclerView.Adapter<CommonRecyclerViewHolder>() {

    var listCenters = ArrayList<CenterDataModel>()

    init {
        this.listCenters.addAll(centers)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommonRecyclerViewHolder {
        return CommonRecyclerViewHolder(
            LayoutInflater.from(activity).inflate(R.layout.layout_common_adapter_item, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return listCenters.size
    }

    override fun getItemId(position: Int): Long {
        return listCenters[position].id.hashCode().toLong()
    }

    override fun onBindViewHolder(_holder: CommonRecyclerViewHolder, position: Int) {


        var centerDataModel = listCenters[_holder.adapterPosition]


        _holder.sdv_image.setImageURI(centerDataModel.pictureUrl)
        _holder.title.text = centerDataModel.name
        _holder.longDescription.text = centerDataModel.longDescription
        if (centerDataModel.memo.isEmpty()) {
            _holder.restTime.text = activity?.getString(R.string.app_no_memo)
        } else {
            _holder.restTime.text = centerDataModel.memo
        }
        _holder.root.setOnClickListener {

            activity.run {
                this?.startActivity(Intent(activity, CenterDetail::class.java).apply {
                    putExtra(Statics.centerData, centerDataModel)
                })
            }
        }


    }

    fun updateData(items: ArrayList<CenterDataModel>) {
        this.listCenters.clear()
        this.listCenters.addAll(items)
        notifyDataSetChanged()
    }


}
