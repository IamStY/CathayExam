package testing.steven.cathaytest.adapter

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import testing.steven.cathaytest.R
import testing.steven.cathaytest.adapter.viewholders.CommonRecyclerViewHolder
import testing.steven.cathaytest.api.Statics
import testing.steven.cathaytest.components.PlantDetailFragment
import testing.steven.cathaytest.datamodel.PlantsDataModel
import kotlin.collections.ArrayList


/***********
 * 植物列表
 */
class PlantsAdapter(var plants: ArrayList<PlantsDataModel>, val activity: AppCompatActivity?) :
    RecyclerView.Adapter<CommonRecyclerViewHolder>() {

    var listPlants = ArrayList<PlantsDataModel>()

    init {
        this.listPlants.addAll(plants)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommonRecyclerViewHolder {
        return CommonRecyclerViewHolder(
            LayoutInflater.from(activity).inflate(R.layout.layout_common_adapter_item, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return listPlants.size
    }

    override fun getItemId(position: Int): Long {
        return listPlants[position].nameEn.hashCode().toLong()
    }

    override fun onBindViewHolder(_holder: CommonRecyclerViewHolder, position: Int) {

        if (_holder.restTime.visibility == View.VISIBLE)
            _holder.restTime.visibility = View.GONE

        if (_holder.indicator.visibility == View.VISIBLE)
            _holder.indicator.visibility = View.GONE
        var centerDataModel = listPlants[_holder.adapterPosition]

        _holder.sdv_image.setImageURI(centerDataModel.imageUrl)
        _holder.title.text = centerDataModel.name
        _holder.longDescription.text = centerDataModel.alsoKnown
        _holder.root.setOnClickListener {
            activity?.run {
                (supportFragmentManager.findFragmentByTag(PlantDetailFragment.getInstanceTag()) as PlantDetailFragment?)?.dismiss()
                PlantDetailFragment().apply {
                    arguments =
                        Bundle().apply { putSerializable(Statics.plantData, centerDataModel) }
                }.show(activity.supportFragmentManager, PlantDetailFragment.getInstanceTag())
            }
        }


    }

    fun updateData(items: ArrayList<PlantsDataModel>) {
        this.listPlants.clear()
        this.listPlants.addAll(items)
        notifyDataSetChanged()
    }


}
