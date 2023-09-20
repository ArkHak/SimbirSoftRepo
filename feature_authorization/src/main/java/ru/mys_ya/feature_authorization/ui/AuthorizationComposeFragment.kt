package ru.mys_ya.feature_authorization.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import ru.mys_ya.core.MainActivityViewModel
import ru.mys_ya.feature_authorization.R

class AuthorizationComposeFragment : Fragment() {
    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private val authorizationViewModel: AuthorizationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    AuthorizationScreen(
                        authorizationViewModel,
                        login = { navigate() }
                    ) { requireActivity().finish() }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.setHideBottomNavigation(true)
    }

    private fun navigate() {
        findNavController().navigate(
            R.id.action_authorization_fragment_to_fragment_help, null,
            NavOptions.Builder()
                .setPopUpTo(R.id.authorization_fragment, true)
                .build()
        )
    }
}
