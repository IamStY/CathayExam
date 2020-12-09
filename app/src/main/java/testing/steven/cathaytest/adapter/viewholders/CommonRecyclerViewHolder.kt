package testing.steven.cathaytest.adapter.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_common_adapter_item.view.*

class CommonRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    var sdv_image = view.sdv_image
    var title = view.title
    var indicator = view.indicator
    var longDescription = view.longDescription
    var restTime = view.restTime
    var root = view.root

}