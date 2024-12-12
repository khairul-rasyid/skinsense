package com.rasyid.skinsense.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rasyid.skinsense.data.local.entity.HistoryEntity
import com.rasyid.skinsense.databinding.ItemHistoryBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryAdapter(
    private val onDeleteClick: (Int) -> Unit
) : ListAdapter<HistoryEntity, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onDeleteClick)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }
    class MyViewHolder(
        private val binding: ItemHistoryBinding,
        private val onDeleteClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryEntity) {
            binding.tvProducts.text = history.product
            binding.tvFeatures.text = history.feature
            binding.tvMaterials.text = history.material
            val date = Date(history.timestamp)
            val formatDate = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault())
            val formattedDate = formatDate.format(date)
            binding.timestamp.text = formattedDate

            binding.btnDelete.setOnClickListener {
                showConfirmationDialog(history.id)
            }
        }

        private fun showConfirmationDialog(id: Int) {
            AlertDialog.Builder(binding.root.context).apply {
                setTitle("Konfirmasi Hapus")
                setMessage("Apakah anda yakin ingin menghapus histori ini?")
                setPositiveButton("Hapus") { _, _ ->
                    onDeleteClick(id)
                }
                setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoryEntity>() {
            override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}