package dk.nodes.template.presentation.ui.main

import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.nodes.template.models.FacebookUser

import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseFragment
import dk.nodes.template.presentation.ui.base.BaseFragment_MembersInjector
import dk.nodes.template.presentation.ui.shift.JobFragment
import dk.nodes.template.presentation.ui.shift.MessageFragment

class EditUserFragment : BaseFragment() {
    private val viewModel by viewModel<MainActivityViewModel>()
    private var listener: JobFragment.OnFragmentInteractionListener? = null

    companion object {
        fun newInstance() = EditUserFragment()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observeNonNull(this) { state ->
            handleUser(state)

        }
        viewModel.fetchUser("10215434751088611")
        }


    private fun handleUser(state: MainActivityViewState) {
        state.facebookUser?.let {


        }
    }


        fun onButtonPressed(uri: Uri) {
            listener?.onFragmentInteraction(uri)
        }


        override fun onDetach() {
            super.onDetach()
            listener = null
        }


        interface OnFragmentInteractionLitener {
            fun onFragmentInteraction(uri: Uri)
        }




}
