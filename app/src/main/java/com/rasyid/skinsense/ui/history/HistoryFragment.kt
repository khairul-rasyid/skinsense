package com.rasyid.skinsense.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyid.skinsense.R
import com.rasyid.skinsense.data.Result
import com.rasyid.skinsense.data.local.entity.HistoryEntity
import com.rasyid.skinsense.databinding.FragmentHistoryBinding
import com.rasyid.skinsense.ui.ViewModelFactory

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val historyViewModel: HistoryViewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        historyViewModel.getHistories().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        if (result.data.isNotEmpty()) {
                            binding.progressBar.visibility = View.GONE
                            setHistory(result.data)
                        } else {
                            showMessage(getString(R.string.no_history))
                        }
                    }
                    is Result.Error -> {
                        showMessage(result.error)
                    }
                }
            }
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvHistory.layoutManager = layoutManager

        return root
    }

    private fun setHistory(listHistory: List<HistoryEntity>) {
        val adapter = HistoryAdapter { id ->
            historyViewModel.deleteHistory(id)
        }
        adapter.submitList(listHistory)
        binding.rvHistory.adapter = adapter
    }

    private fun showMessage(message: String) {
        binding.rvHistory.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.tvMessage.text = message
        binding.tvMessage.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}