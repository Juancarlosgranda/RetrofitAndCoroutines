package com.juancarlos.retrofitandcoroutines.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juancarlos.retrofitandcoroutines.R
import com.juancarlos.retrofitandcoroutines.data.model.PhotosModel
import kotlinx.android.synthetic.main.layout_list_item_photos.view.*

class PhotosAdapter (context: Context, private var photoList: List<PhotosModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = "AppDebug"
    private val activity = context
    private var items: List<PhotosModel> = photoList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReceptionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item_photos, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ReceptionViewHolder -> {
                holder.bind(items.get(position))
//                holder.itemView.setOnClickListener{
//                    Toast.makeText(activity,"clicked " + position, Toast.LENGTH_SHORT).show()
//                    Log.d(TAG,"ITEM: "+ " : "+holder.reception_shipment.text)
//
//                    val textBundle = Bundle()
//                    textBundle.putString("nroReception",holder.reception_shipment.text.toString())
//                    findViewController.navigate(R.id.see_reception_order, textBundle)
//                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setListData(photoList: List<PhotosModel>) {
        this.photoList = photoList
        notifyDataSetChanged()
    }

    fun submitList(receptionList: List<PhotosModel>){
        items = receptionList
    }

    inner class ReceptionViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val title = itemView.text_title
        val subtitle = itemView.text_receiver

        fun bind(photoModel: PhotosModel){
            title.text = photoModel.title
            subtitle.text = photoModel.url
        }
    }

}