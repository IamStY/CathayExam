package testing.steven.cathaytest.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_plant_detail.*
import testing.steven.cathaytest.R
import testing.steven.cathaytest.api.Statics
import testing.steven.cathaytest.components.bases.FullScreenBaseDialogFragment
import testing.steven.cathaytest.datamodel.PlantsDataModel

/***********
 * 植物詳情頁面
 */
class PlantDetailFragment : FullScreenBaseDialogFragment() {
    companion object {
        @JvmStatic
        fun getInstanceTag(): String {
            return this::class.java.name
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVm()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_plant_detail, container)
        return view
    }

    private fun initVm() {
        activity?.run {
            injectUI(arguments?.getSerializable(Statics.plantData) as PlantsDataModel)
        }
    }

    private fun injectUI(plant: PlantsDataModel) {
        imgBackground.setImageURI(plant.imageUrl)
        name_tw.text = plant.name
        name_en.text = plant.nameEn
        plants_alsoKnown.text = plant.alsoKnown
        plant_brief.text = plant.brief
        plant_feature.text = plant.feature
        plant_plants_func.text = plant.longDescription
        plants_updated.text = plants_updated.text.toString() + plant.updatedAt
        toolbar.title = plant.name
        toolbar.setNavigationOnClickListener {
            dismiss()
        }


    }


}