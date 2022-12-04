package io.github.obaya884.randomuser.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.airbnb.epoxy.EpoxyVisibilityTracker
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.github.obaya884.randomuser.databinding.FragmentRandomUserListBinding

@AndroidEntryPoint
class RandomUserListFragment : Fragment() {
    private var _viewBinding: FragmentRandomUserListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val viewBinding get() = _viewBinding!!

    private val viewModel: RandomUserListViewModel by viewModels()

    private val controller = RandomUserListController(
        object : RandomUserListController.Listener {
            override fun onNextPage() {
                viewModel.getUsersNextPage()
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentRandomUserListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUsersFirstPage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // フラグメントはビューよりも持続するため、onDestroyView()でViewBindingの参照を解放する
        _viewBinding = null
    }

    private fun setupRecyclerView() {
        viewBinding.recyclerView.apply {
            adapter = controller.adapter
            EpoxyVisibilityTracker().attach(this)
        }
    }

    private fun observeViewModel() {
        viewModel.uiModel.observe(viewLifecycleOwner) { uiModel ->
            when (uiModel.loadState) {
                is LoadState.Initialized -> {
                }

                is LoadState.Loading -> {
                }

                is LoadState.Succeeded -> {
                    controller.setData(uiModel)
                }

                is LoadState.Error -> Snackbar.make(
                    requireView(),
                    uiModel.loadState.throwable.message.toString(),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}